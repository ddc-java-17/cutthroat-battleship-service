package edu.cnm.deepdive.jata.model.entity;

import jakarta.persistence.Entity;
import java.util.UUID;
import net.jcip.annotations.Immutable;

@Entity
@Immutable
public class ShotsStatus {

  private UUID gameId;

  private UUID playerId;

  private int shotCoordX;

  private int shotCoordY;

  private boolean isFleetSunk;

  private boolean isHit;

  public UUID getGameId() {
    return gameId;
  }

  public UUID getPlayerId() {
    return playerId;
  }

  public int getShotCoordX() {
    return shotCoordX;
  }

  public int getShotCoordY() {
    return shotCoordY;
  }

  public boolean isFleetSunk() {
    return isFleetSunk;
  }

  public boolean isHit() {
    return isHit;
  }
}
