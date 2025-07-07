package org.hongryeo.ttanulim.api.dto;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hongryeo.ttanulim.common.domain.Shakemap;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShakemapResponse {

  private String id;
  private Instant createdAt;
  private Instant updatedAt;
  private String mii;
  private String pga;
  private String pgv;
  private String psa0p3;
  private String psa0p6;
  private String psa1p0;
  private String psa3p0;

  public static ShakemapResponse from(Shakemap shakemap) {
    return new ShakemapResponse(
        shakemap.getShakemapId(),
        shakemap.getCreatedAt(),
        shakemap.getUpdatedAt(),
        shakemap.getMii(),
        shakemap.getPga(),
        shakemap.getPgv(),
        shakemap.getPsa0p3(),
        shakemap.getPsa0p6(),
        shakemap.getPsa1p0(),
        shakemap.getPsa3p0()
    );
  }
}
