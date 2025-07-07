package org.hongryeo.ttanulim.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shakemap")
public class Shakemap {

  @Id
  @Column(name = "shakemap_id")
  private String shakemapId;

  @Column(nullable = false)
  private Instant createdAt;

  private Instant updatedAt;

  @Column(columnDefinition = "TEXT")
  private String mii;

  @Column(columnDefinition = "TEXT")
  private String pga;

  @Column(columnDefinition = "TEXT")
  private String pgv;

  @Column(columnDefinition = "TEXT")
  private String psa0p3;

  @Column(columnDefinition = "TEXT")
  private String psa0p6;

  @Column(columnDefinition = "TEXT")
  private String psa1p0;

  @Column(columnDefinition = "TEXT")
  private String psa3p0;
}
