package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.entity.ShotsStatus;
import java.util.List;

public interface AbstractShotStatusService {


  List<ShotsStatus> getStatus();
}
