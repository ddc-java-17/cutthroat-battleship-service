package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.entity.ShotStatus;
import java.util.List;

public interface AbstractShotStatusService {


  List<ShotStatus> getStatus();
}
