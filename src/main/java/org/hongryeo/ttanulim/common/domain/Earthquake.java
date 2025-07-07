package org.hongryeo.ttanulim.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "earthquake")
public class Earthquake {

  @Id
  private String id;

  @Column(nullable = false, length = 255)
  private String title;

  @Column(nullable = false)
  private double mag;

  @Column(nullable = false, length = 255)
  private String place;

  @Column(nullable = false)
  private Instant createdAt;

  private Instant updatedAt;

  @Column(length = 10)
  private String alert;

  @Column(length = 10)
  private String status;

  private Boolean tsunami;

  @Column(length = 10)
  private String magType;

  @Column(nullable = false)
  private double lng;

  @Column(nullable = false)
  private double lat;

  @Column(nullable = false)
  private double depth;

  @ManyToOne
  @JoinColumn(name = "shakemap_id")
  private Shakemap shakemap;
}
