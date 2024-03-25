package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipLocationRepository extends JpaRepository<ShipLocation, Long> {

  Integer findShipLocationByShipNumber(int shipNumber);

  Integer findShipLocationByShipNumberAndShipCoordXAndShipCoordY(int shipNumber, int shipCoordX, int shipCoordY);

  Boolean findShipLocationByUserGameAndShipCoordXAndShipCoordY(UserGame userGame, int shipCoordX, int shipCoordy);
}
