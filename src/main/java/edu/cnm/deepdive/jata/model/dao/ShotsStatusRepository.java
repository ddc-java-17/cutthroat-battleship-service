package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.ShotsStatus;
import java.util.List;

public interface ShotsStatusRepository extends ReadOnlyRepository<ShotsStatus> {

  List<ShotsStatus> findAllByUserGameIdAndShipXCoordAndShipYCoordAndShotXCoordAndShotYCoord(int xcoord, int ycoord);



}
