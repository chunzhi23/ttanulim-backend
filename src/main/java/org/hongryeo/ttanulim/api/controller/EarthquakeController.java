package org.hongryeo.ttanulim.api.controller;

import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hongryeo.ttanulim.api.dto.EarthquakeResponse;
import org.hongryeo.ttanulim.api.service.EarthquakeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/earthquake")
public class EarthquakeController {

  private final EarthquakeService earthquakeService;

  @GetMapping
  public ResponseEntity<List<EarthquakeResponse>> getEarthquakes(
      @RequestParam(required = false, value = "startTime") Instant startTime,
      @RequestParam(required = false, value = "endTime") Instant endTime,
      @RequestParam(required = false, value = "sort") String sortOption,
      @RequestParam(required = false, value = "minMag") Double minMag
  ) {
    List<EarthquakeResponse> earthquakes = earthquakeService.getEarthquakeList(startTime, endTime,
        sortOption, minMag);
    return ResponseEntity.ok(earthquakes);
  }
}
