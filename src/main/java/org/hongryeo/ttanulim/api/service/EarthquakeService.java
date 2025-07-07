package org.hongryeo.ttanulim.api.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.hongryeo.ttanulim.api.dto.EarthquakeResponse;
import org.hongryeo.ttanulim.api.repository.EarthquakeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EarthquakeService {

  private final EarthquakeRepository earthquakeRepository;

  @Transactional
  public List<EarthquakeResponse> getEarthquakeList(
      Instant startTime,
      Instant endTime,
      String sortOption,
      Double minMag
  ) {
    Instant now = Instant.now();

    if (startTime == null) {
      startTime = now.minus(Duration.ofHours(24));
    }
    if (endTime == null) {
      endTime = now;
    }
    if (minMag == null) {
      minMag = 2.5;
    }
    if (sortOption == null) {
      sortOption = "recent";
    }

    Sort sort = switch (sortOption.toUpperCase()) {
      case "OLDEST" -> Sort.by("createdAt").ascending();
      case "MAG_DESC" -> Sort.by("mag").descending();
      case "MAG_ASC" -> Sort.by("mag").ascending();
      default -> Sort.by("createdAt").descending();
    };

    return earthquakeRepository.filterEarthquakes(
            startTime, endTime, minMag, sort
        ).stream()
        .map(EarthquakeResponse::from)
        .collect(Collectors.toList());
  }
}
