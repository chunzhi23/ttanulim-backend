package org.hongryeo.ttanulim.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import org.hongryeo.ttanulim.core.domain.Shakemap;
import org.hongryeo.ttanulim.core.dto.EarthquakeGeoJsonDto;
import org.hongryeo.ttanulim.core.dto.ShakemapDetailDto;
import org.hongryeo.ttanulim.core.repository.ShakemapRepository;
import org.hongryeo.ttanulim.core.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShakemapService {

  private static final Logger logger = LoggerFactory.getLogger(ShakemapService.class);

  private final ShakemapRepository shakemapRepository;
  private final ObjectMapper mapper;

  public ShakemapService(ShakemapRepository shakemapRepository) {
    this.shakemapRepository = shakemapRepository;
    this.mapper = new ObjectMapper();
  }

  public Shakemap processShakemap(EarthquakeGeoJsonDto.Feature feature) {
    try {
      String detailJsonStr = HttpUtil.fetchRawJson(feature.properties.getDetail());
      if (detailJsonStr == null) {
        return null;
      }

      ShakemapDetailDto shakemapDto = mapper.readValue(detailJsonStr, ShakemapDetailDto.class);

      Shakemap shakemap = new Shakemap();
      shakemap.setShakemapId(feature.id);
      shakemap.setCreatedAt(Instant.ofEpochMilli(feature.properties.time));
      shakemap.setUpdatedAt(Instant.ofEpochMilli(feature.properties.updated));
      shakemap.setMii(getShakemapContent("download/cont_mi.json", shakemapDto));
      shakemap.setPga(getShakemapContent("download/cont_pga.json", shakemapDto));
      shakemap.setPgv(getShakemapContent("download/cont_pgv.json", shakemapDto));
      shakemap.setPsa0p3(getShakemapContent("download/cont_psa0p3.json", shakemapDto));
      shakemap.setPsa0p6(getShakemapContent("download/cont_psa0p6.json", shakemapDto));
      shakemap.setPsa1p0(getShakemapContent("download/cont_psa1p0.json", shakemapDto));
      shakemap.setPsa3p0(getShakemapContent("download/cont_psa3p0.json", shakemapDto));

      return shakemapRepository.save(shakemap);

    } catch (Exception e) {
      logger.warn("Shakemap parsing failed for {}: {}", feature.id, e.getMessage());
      return null;
    }
  }


  private String getShakemapContent(String key, ShakemapDetailDto dto) {
    return HttpUtil.fetchRawJson(dto.getContentUrl(key));
  }
}
