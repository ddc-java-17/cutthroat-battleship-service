package edu.cnm.deepdive.jata.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cnm.deepdive.jata.view.ShotView;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import java.util.Objects;

/**
 * This is a helper class that maintains the x and y coordinates in one field
 */
@Embeddable
@JsonView({ShotView.Summary.class})
public class Location {

  @Min(1)
  private int x;

  @Min(1)
  private int y;

  /**
   * Default, no parameter constructor
   */
  public Location() {
  }

  /**
   * Constructor that passes the x and y values
   *
    * @param x  abscissa of the location
   * @param y  ordinate of the location
   */
  public Location(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   *
    * @return Returns the x value of a location
   */
  public int getX() {
    return x;
  }

  /**
   * Annotates the x value of a location
   *
   * @param x  abscissa of the location
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Returns the y value of a location
   *
    * @return  y value of a location
   */
  public int getY() {
    return y;
  }

  /**
   * Annotates the y value of a location
   *
   * @param y  ordinate of the location
   */
  public void setY(int y) {
    this.y = y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public boolean equals(Object obj) {
    boolean equals;
    if (this == obj) {
      equals = true;
    } else if (obj instanceof Location other) {
      equals = (this.x == other.x && this.y == other.y);
    } else {
      equals = false;
    }
    return equals;
  }

}
