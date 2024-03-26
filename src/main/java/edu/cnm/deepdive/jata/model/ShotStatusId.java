package edu.cnm.deepdive.jata.model;

import edu.cnm.deepdive.jata.model.entity.Shot;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;

/**
 * This class tracks the Id of every shot and its status.
 */
@Embeddable
public class ShotStatusId implements Serializable {

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "shot_id")
  private Shot shot;

  /**
   * Empty shotStatusId constructor
   */
  public ShotStatusId() {
  }

  /**
   * Initialize sequence with specialized shot.
   * @param shot
   */
  public ShotStatusId(Shot shot) {
    this.shot = shot;
  }

  /**
   * Gets the shots
   * @return shots
   */
  public Shot getShot() {
    return shot;
  }
}
