package edu.cnm.deepdive.jata.model;

import edu.cnm.deepdive.jata.model.entity.Shot;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class ShotStatusId implements Serializable {

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name ="shot_id")
  private Shot shot;

  public ShotStatusId() {
  }

  public ShotStatusId(Shot shot) {
    this.shot = shot;
  }

  public Shot getShot() {
    return shot;
  }

  public void setShot(Shot shot) {
    this.shot = shot;
  }
}
