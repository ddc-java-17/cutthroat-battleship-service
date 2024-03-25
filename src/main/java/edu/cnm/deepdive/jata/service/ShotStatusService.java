package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dao.ShotsStatusRepository;
import edu.cnm.deepdive.jata.model.entity.ShotsStatus;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShotStatusService implements AbstractShotStatusService {

  private final ShotsStatusRepository shotsStatusRepository;

  @Autowired
  public ShotStatusService(ShotsStatusRepository shotsStatusRepository) {
    this.shotsStatusRepository = shotsStatusRepository;
  }

  @Override
  public List<ShotsStatus> getStatus() {
    return shotsStatusRepository.findAllByUserGameIdAndShipXCoordAndShipYCoordAndShotXCoordAndShotYCoord();
  }
}
