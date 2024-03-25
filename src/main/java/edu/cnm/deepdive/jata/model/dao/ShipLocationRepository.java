package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.User;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShipLocationRepository extends JpaRepository<ShipLocation, Long> {



  Integer findShipLocationByShipNumber(int shipNumber);

  Integer findShipLocationByShipNumberAndAndShipCoordXAndAndShipCoordY(int shipNumber, int shipCoordX, int shipCoordY);

  @Query("SELECT count(sl) AS count FROM ShipLocation AS sl WHERE sl.userGame = :userGame")
  ShipsCount findShipLocationByUserGame(UserGame userGame);

  @Query("SELECT sl.shipCoordX AS x, sl.shipCoordY AS y FROM ShipLocation AS sl WHERE sl.shipNumber = :number AND sl.userGame = :userGame")
  ShipValid findShipLocationByShipNumberAndUserGame(int number, UserGame userGame);
}
