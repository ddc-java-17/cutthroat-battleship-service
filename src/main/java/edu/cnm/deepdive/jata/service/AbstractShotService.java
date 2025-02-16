package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dto.GameDTO;
import edu.cnm.deepdive.jata.model.dto.ShotDTO;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.List;
import java.util.UUID;

public interface AbstractShotService {
  /**
   * submits shots to a game
   * @param key UUID
   * @param shots List of Shots
   * @param currentUser user
   * @return shots
   */
  GameDTO submitShots(UUID key, List<ShotDTO> shots, User currentUser);

}
