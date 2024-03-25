package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipLocationRepository extends JpaRepository<ShipLocation, Long> {

  Integer findShipLocationByShipNumber(int shipNumber);

  Integer findShipLocationByShipNumberAndAndShipCoordXAndAndShipCoordY(int shipNumber, int shipCoordX, int shipCoordY);
}
