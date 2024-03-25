package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.ShotStatusId;
import edu.cnm.deepdive.jata.model.entity.ShotStatus;

public interface ShotsStatusRepository extends ReadOnlyRepository<ShotStatus, ShotStatusId> {

 // List<ShotsStatus> findAllByUserGameIdAndShipXCoordAndShipYCoordAndShotXCoordAndShotYCoord(int xcoord, int ycoord);



}
