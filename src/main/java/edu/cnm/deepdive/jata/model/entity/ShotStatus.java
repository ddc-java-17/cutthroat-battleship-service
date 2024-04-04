package edu.cnm.deepdive.jata.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

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

  public Shot getShot() {
    return shot;
  }

  public boolean isHit() {
    return hit;
  }

}
