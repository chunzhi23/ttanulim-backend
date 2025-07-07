package org.hongryeo.ttanulim.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Optional;
import org.hongryeo.ttanulim.common.domain.Earthquake;
import org.hongryeo.ttanulim.common.domain.Shakemap;
import org.hongryeo.ttanulim.core.dto.EarthquakeGeoJsonDto;
import org.hongryeo.ttanulim.core.repository.EarthquakeCoreRepository;
import org.hongryeo.ttanulim.core.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EarthquakeCoreService {

  private static final Logger logger = LoggerFactory.getLogger(EarthquakeCoreService.class);
  private static final String FEED_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_day.geojson";

  private final EarthquakeCoreRepository earthquakeCoreRepository;
  private final ShakemapCoreService shakemapCoreService;
  private final ObjectMapper mapper;

  public EarthquakeCoreService(EarthquakeCoreRepository earthquakeCoreRepository,
      ShakemapCoreService shakemapCoreService) {
    this.earthquakeCoreRepository = earthquakeCoreRepository;
    this.shakemapCoreService = shakemapCoreService;
    this.mapper = new ObjectMapper();
  }

  @Transactional
  @Scheduled(fixedRate = 10000)
  public void scheduleTaskWithFixedRate() {
    String json = HttpUtil.fetchRawJson(FEED_URL);
    if (json == null) {
      return;
    }

    try {
      EarthquakeGeoJsonDto dto = mapper.readValue(json, EarthquakeGeoJsonDto.class);

      for (EarthquakeGeoJsonDto.Feature feature : dto.getFeatures()) {
        processEarthquakeFeature(feature);
      }
    } catch (IOException e) {
      logger.warn("Failed to parse Earthquake feed JSON: {}", e.getMessage());
    }
  }

  @Transactional
  protected void processEarthquakeFeature(EarthquakeGeoJsonDto.Feature feature) {
    Earthquake incoming = feature.toEarthquakeEntity();

    if (feature.properties.isShakemap()) {
      Shakemap shakemap = shakemapCoreService.processShakemap(feature);
      if (shakemap != null) {
        incoming.setShakemap(shakemap);
      }
    }

    Optional<Earthquake> existingOpt = earthquakeCoreRepository.findByIdCustom(incoming.getId());

    if (existingOpt.isEmpty()) {
      earthquakeCoreRepository.save(incoming);
    } else {
      Earthquake existing = existingOpt.get();
      if (!"reviewed".equalsIgnoreCase(existing.getStatus()) &&
          "reviewed".equalsIgnoreCase(incoming.getStatus())) {
        earthquakeCoreRepository.updateEarthquake(incoming);
      }
    }
  }
}
