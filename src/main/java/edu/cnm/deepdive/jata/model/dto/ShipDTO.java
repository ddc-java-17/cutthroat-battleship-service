package edu.cnm.deepdive.jata.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.Min;

public class ShipDTO {
  public static final int MIN_SHIP_NUMBER = 1;
  public static final int MIN_X_COORD = 1;
  public static final int MIN_Y_COORD = 1;
  public static final int MIN_SHIP_LENGTH = 2;

  @JsonProperty(access = Access.READ_WRITE)
  @Min(MIN_SHIP_NUMBER)
  private int shipNumber;

  @JsonProperty(access = Access.READ_WRITE)
  @Min(MIN_X_COORD)
  private int shipOriginX;

  @JsonProperty(access = Access.READ_WRITE)
  @Min(MIN_Y_COORD)
  private int shipOriginY;

  @JsonProperty(access = Access.READ_WRITE)
  @Min(MIN_SHIP_LENGTH)
  private int shipLength;

  @JsonProperty(access = Access.READ_WRITE)
  private boolean isVertical;

  public int getShipNumber() {
    return shipNumber;
  }

  public void setShipNumber(int shipNumber) {
    this.shipNumber = shipNumber;
  }

  public int getShipOriginX() {
    return shipOriginX;
  }

  public void setShipOriginX(int shipOriginX) {
    this.shipOriginX = shipOriginX;
  }

  public int getShipOriginY() {
    return shipOriginY;
  }

  public void setShipOriginY(int shipOriginY) {
    this.shipOriginY = shipOriginY;
  }

  public int getShipLength() {
    return shipLength;
  }

  public void setShipLength(int shipLength) {
    this.shipLength = shipLength;
  }

  public boolean isVertical() {
    return isVertical;
  }

  public void setVertical(boolean vertical) {
    isVertical = vertical;
  }
}
