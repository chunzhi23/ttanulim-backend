package org.hongryeo.ttanulim.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUtil {

  public static String fetchRawJson(String url) {
    if (url == null || url.isBlank()) {
      return null;
    }
    try (InputStream in = new URL(url).openStream()) {
      return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      return null;
    }
  }
}
