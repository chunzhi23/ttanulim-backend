package org.hongryeo.ttanulim.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShakemapDetailDto {

  @JsonProperty("properties")
  public Properties properties;

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Properties {

    public Products products;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Products {

    public Shakemap[] shakemap;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Shakemap {

    public Map<String, ContentItem> contents;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ContentItem {

    public String url;
  }

  public String getContentUrl(String key) {
    if (properties == null ||
        properties.products == null ||
        properties.products.shakemap == null ||
        properties.products.shakemap.length == 0) {
      return null;
    }
    return properties.products.shakemap[0].contents.getOrDefault(key, new ContentItem()).url;
  }
}