package org.hongryeo.ttanulim.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;

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

  /**
   * Getters and Setters
   */

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public double getMag() {
    return mag;
  }

  public void setMag(double mag) {
    this.mag = mag;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
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

  public String getAlert() {
    return alert;
  }

  public void setAlert(String alert) {
    this.alert = alert;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Boolean getTsunami() {
    return tsunami;
  }

  public void setTsunami(Boolean tsunami) {
    this.tsunami = tsunami;
  }

  public String getMagType() {
    return magType;
  }

  public void setMagType(String magType) {
    this.magType = magType;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getDepth() {
    return depth;
  }

  public void setDepth(double depth) {
    this.depth = depth;
  }

  public Shakemap getShakemap() {
    return shakemap;
  }

  public void setShakemap(Shakemap shakemap) {
    this.shakemap = shakemap;
  }
}
