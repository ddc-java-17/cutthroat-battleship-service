package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.ShotStatusId;
import edu.cnm.deepdive.jata.model.entity.ShotStatus;

/**
 * This repository manages the status of shot objects and returns the status of shot objects.
 */
public interface ShotsStatusRepository extends ReadOnlyRepository<ShotStatus, ShotStatusId> {

 // List<ShotsStatus> findAllByUserGameIdAndShipXCoordAndShipYCoordAndShotXCoordAndShotYCoord(int xcoord, int ycoord);



}
