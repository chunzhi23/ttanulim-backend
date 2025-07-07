package org.hongryeo.ttanulim.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import java.util.List;
import org.hongryeo.ttanulim.common.domain.Earthquake;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EarthquakeGeoJsonDto {

  private List<Feature> features;

  public List<Feature> getFeatures() {
    return features;
  }

  public void setFeatures(List<Feature> features) {
    this.features = features;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Feature {

    public String id;
    public Properties properties;
    public Geometry geometry;

    public Earthquake toEarthquakeEntity() {
      Earthquake e = new Earthquake();
      e.setId(id);
      e.setTitle(properties.title);
      e.setMag(properties.mag);
      e.setPlace(properties.place);
      e.setCreatedAt(Instant.ofEpochMilli(properties.time));
      e.setUpdatedAt(Instant.ofEpochMilli(properties.updated));
      e.setAlert(properties.alert);
      e.setStatus(properties.status);
      e.setTsunami(properties.tsunami);
      e.setMagType(properties.magType);
      e.setLng(geometry.coordinates.get(0));
      e.setLat(geometry.coordinates.get(1));
      e.setDepth(geometry.coordinates.get(2));
      return e;
    }

  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Properties {

    public double mag;
    public String place;
    public long time;
    public long updated;
    public String detail;
    public String alert;
    public String status;
    public Boolean tsunami;
    public String types;
    public String magType;
    public String title;

    public String getDetail() {
      return detail;
    }

    public Boolean isShakemap() {
      return types != null && types.contains("shakemap");
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Geometry {

    public List<Double> coordinates;
  }
}
