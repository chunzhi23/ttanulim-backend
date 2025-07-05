package org.hongryeo.ttanulim.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Optional;
import org.hongryeo.ttanulim.core.domain.Earthquake;
import org.hongryeo.ttanulim.core.domain.Shakemap;
import org.hongryeo.ttanulim.core.dto.EarthquakeGeoJsonDto;
import org.hongryeo.ttanulim.core.repository.EarthquakeRepository;
import org.hongryeo.ttanulim.core.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EarthquakeService {

  private static final Logger logger = LoggerFactory.getLogger(EarthquakeService.class);
  private static final String FEED_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_day.geojson";

  private final EarthquakeRepository earthquakeRepository;
  private final ShakemapService shakemapService;
  private final ObjectMapper mapper;

  public EarthquakeService(EarthquakeRepository earthquakeRepository,
      ShakemapService shakemapService) {
    this.earthquakeRepository = earthquakeRepository;
    this.shakemapService = shakemapService;
    this.mapper = new ObjectMapper();
  }

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

  private void processEarthquakeFeature(EarthquakeGeoJsonDto.Feature feature) {
    Earthquake incoming = feature.toEarthquakeEntity();

    if (feature.properties.isShakemap()) {
      Shakemap shakemap = shakemapService.processShakemap(feature);
      if (shakemap != null) {
        incoming.setShakemap(shakemap);
      }
    }

    Optional<Earthquake> existingOpt = earthquakeRepository.findByIdCustom(incoming.getId());

    if (existingOpt.isEmpty()) {
      earthquakeRepository.save(incoming);
    } else {
      Earthquake existing = existingOpt.get();
      if (!"reviewed".equalsIgnoreCase(existing.getStatus()) &&
          "reviewed".equalsIgnoreCase(incoming.getStatus())) {
        earthquakeRepository.updateEarthquake(incoming);
      }
    }
  }

}
