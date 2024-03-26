package edu.cnm.deepdive.jata.model.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import edu.cnm.deepdive.jata.model.ShotStatusId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import net.jcip.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * This class records the status of every shot made by every user in a game.
 */
@SuppressWarnings({"JpaDataSourceORMInspection", "SqlDialectInspection", "SqlResolve"})
@Entity
@Immutable
@Subselect("SELECT * FROM shot_status")
public class ShotStatus {

  @Id
  @JsonUnwrapped
  private ShotStatusId id;
  /**
   * Gets the player that sent the shots.
   */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "player_id")
  public UserGame player;
  /**
   * Returns whether each shot was a hit or a miss.
   */
  public boolean hit;

  /**
   * Gets the shot id and that shots status
   * @return shotId
   */
  public ShotStatusId getId() {
    return id;
  }

  /**
   * Gets whether the shot was a hit or a miss
   * @return boolean hit or miss
   */
  public boolean isHit() {
    return hit;
  }

  /**
   * Gets the player who sent the shot
   * @return playerId
   */
  public UserGame getPlayer() {
    return player;
  }

}
