package org.hongryeo.ttanulim.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

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

  /**
   * Getters and Setters
   */

  public String getShakemapId() {
    return shakemapId;
  }

  public void setShakemapId(String shakemapId) {
    this.shakemapId = shakemapId;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getMii() {
    return mii;
  }

  public void setMii(String mii) {
    this.mii = mii;
  }

  public String getPga() {
    return pga;
  }

  public void setPga(String pga) {
    this.pga = pga;
  }

  public String getPgv() {
    return pgv;
  }

  public void setPgv(String pgv) {
    this.pgv = pgv;
  }

  public String getPsa0p3() {
    return psa0p3;
  }

  public void setPsa0p3(String psa0p3) {
    this.psa0p3 = psa0p3;
  }

  public String getPsa0p6() {
    return psa0p6;
  }

  public void setPsa0p6(String psa0p6) {
    this.psa0p6 = psa0p6;
  }

  public String getPsa1p0() {
    return psa1p0;
  }

  public void setPsa1p0(String psa1p0) {
    this.psa1p0 = psa1p0;
  }

  public String getPsa3p0() {
    return psa3p0;
  }

  public void setPsa3p0(String psa3p0) {
    this.psa3p0 = psa3p0;
  }
}
