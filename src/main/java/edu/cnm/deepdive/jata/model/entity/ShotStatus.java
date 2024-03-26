package edu.cnm.deepdive.jata.model.entity;

import edu.cnm.deepdive.jata.model.ShotStatusId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT * FROM shot_status")
public class ShotStatus {

  @Id
  private ShotStatusId id;

  private boolean hit;

  public ShotStatusId getId() {
    return id;
  }

  public boolean isHit() {
    return hit;
  }

}
