package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This repository manages ship objects and there location in the ShipLocation table.
 */
public interface ShipLocationRepository extends JpaRepository<ShipLocation, Long> {

  /**
   * This Query is used to determine if a user already has ships in the ShipLocation table.
   * This returns a count of the total locations in the ShipLocation table associated
   * with a particular userGame.
   * @param userGame
   * @return
   */
  @Query("SELECT count(sl) AS count FROM ShipLocation AS sl WHERE sl.userGame = :userGame")
  ShipsCount findShipLocationByUserGame(UserGame userGame);

}
