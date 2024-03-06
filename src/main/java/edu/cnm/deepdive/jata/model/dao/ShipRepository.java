package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.Fleet;
import edu.cnm.deepdive.jata.model.entity.Ship;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {

  Optional<Ship> findShipsByFleet(Fleet fleet);
}
