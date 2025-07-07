package org.hongryeo.ttanulim.api.dto;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hongryeo.ttanulim.common.domain.Earthquake;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class EarthquakeResponse {

  private String id;
  private String title;
  private Double mag;
  private String place;
  private Instant createdAt;
  private Instant updatedAt;
  private String alert;
  private Boolean tsunami;
  private String magType;
  private Double lng;
  private Double lat;
  private Double depth;

  public static EarthquakeResponse from(Earthquake earthquake) {
    return new EarthquakeResponse(
        earthquake.getId(),
        earthquake.getTitle(),
        earthquake.getMag(),
        earthquake.getPlace(),
        earthquake.getCreatedAt(),
        earthquake.getUpdatedAt(),
        earthquake.getAlert(),
        earthquake.getTsunami(),
        earthquake.getMagType(),
        earthquake.getLng(),
        earthquake.getLat(),
        earthquake.getDepth()
    );
  }
}
