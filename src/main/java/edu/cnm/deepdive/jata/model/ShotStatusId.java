package edu.cnm.deepdive.jata.model;

import edu.cnm.deepdive.jata.model.entity.Shot;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;
import org.hibernate.annotations.Subselect;

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

  @Override
  public int hashCode() {
    return Objects.hash(shot);
  }

  @Override
  public boolean equals(Object obj) {
    boolean equals;
    if(this==obj) {
      equals = true;
    } else if(obj instanceof ShotStatusId other) {
      equals = (this.shot.equals(other.shot));
    } else {
      equals = false;
    }
    return equals;
  }
}
