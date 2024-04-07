package edu.cnm.deepdive.jata.service;

import edu.cnm.deepdive.jata.model.dto.ShotDTO;
import edu.cnm.deepdive.jata.model.entity.Shot;
import edu.cnm.deepdive.jata.model.entity.User;
import java.util.List;
import java.util.UUID;

public interface AbstractShotService {
  /**
   * submits shots to a game
   * @param key {@Link UUID}
   * @param shots List<Shots>
   * @param currentUser {@Link user}
   * @return shots
   */
  List<ShotDTO> submitShots(UUID key, List<ShotDTO> shots, User currentUser);


  /**
   * Gets shots from current game.
   * @param key
   * @param guessKey
   * @param currentUser
   * @return
   */
  ShotDTO getShot(UUID key, UUID guessKey, User currentUser);

}
