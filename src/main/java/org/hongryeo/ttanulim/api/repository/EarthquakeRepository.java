package org.hongryeo.ttanulim.api.repository;

import java.time.Instant;
import java.util.List;
import org.hongryeo.ttanulim.common.domain.Earthquake;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EarthquakeRepository extends JpaRepository<Earthquake, String> {

  @EntityGraph(attributePaths = "shakemap")
  @Query("""
          SELECT e FROM Earthquake e
          WHERE e.createdAt BETWEEN :startTime AND :endTime
          AND e.mag >= :minMag
      """)
  List<Earthquake> filterEarthquakes(
      @Param("startTime") Instant startTime,
      @Param("endTime") Instant endTime,
      @Param("minMag") Double minMag,
      Sort sort
  );
}
