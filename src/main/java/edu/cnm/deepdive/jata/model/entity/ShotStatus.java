package edu.cnm.deepdive.jata.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * This entity records the hit/no hit status of each shot
 */
@Entity
@Immutable
@Subselect("SELECT * FROM shot_status")
public class ShotStatus {

  @Id
  @MapsId
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name ="shot_id")
  private Shot shot;

  private boolean hit;

  /**
   * Returns the value of a particular shot entity to be tested
   * @return
   */
  public Shot getShot() {
    return shot;
  }

  /**
   * Returns a flag indicating whether this shot hit a ship or not.
   * @return
   */
  public boolean isHit() {
    return hit;
  }

}
