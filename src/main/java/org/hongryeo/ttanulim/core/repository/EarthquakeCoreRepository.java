package org.hongryeo.ttanulim.core.repository;

import java.util.Optional;
import org.hongryeo.ttanulim.common.domain.Earthquake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EarthquakeCoreRepository extends JpaRepository<Earthquake, String> {

  @Query("SELECT e FROM Earthquake e WHERE e.id = :id")
  Optional<Earthquake> findByIdCustom(@Param("id") String id);

  @Modifying
  @Query("UPDATE Earthquake e SET "
      + "e.title = :#{#e.title}, "
      + "e.mag = :#{#e.mag}, "
      + "e.place = :#{#e.place}, "
      + "e.createdAt = :#{#e.createdAt}, "
      + "e.updatedAt = :#{#e.updatedAt}, "
      + "e.alert = :#{#e.alert}, "
      + "e.status = :#{#e.status}, "
      + "e.tsunami = :#{#e.tsunami}, "
      + "e.magType = :#{#e.magType}, "
      + "e.lng = :#{#e.lng}, "
      + "e.lat = :#{#e.lat}, "
      + "e.depth = :#{#e.depth}, "
      + "e.shakemap = :#{#e.shakemap} "
      + "WHERE e.id = :#{#e.id}")
  void updateEarthquake(@Param("e") Earthquake e);
}
