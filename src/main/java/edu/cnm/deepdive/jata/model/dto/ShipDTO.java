package edu.cnm.deepdive.jata.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import edu.cnm.deepdive.jata.model.Direction;
import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import jakarta.validation.constraints.Min;
import java.util.Collection;

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

  @JsonIgnore
  private Collection<ShipLocation> shipLocations;

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

  public Collection<ShipLocation> getShipLocations() {
    return shipLocations;
  }

  public void setShipLocations(
      Collection<ShipLocation> shipLocations) {
    this.shipLocations = shipLocations;
  }

  private void DtoToCollection(ShipDTO ship){
    ShipDTO shipDTO;
    int[] indexMod;
    indexMod = (ship.isVertical())
        ? new int[]{Direction.VERTICAL.getVerticalIndex(), Direction.VERTICAL.getHorizontalIndex()}
        : new int[]{Direction.HORIZONTAL.getVerticalIndex(),
            Direction.HORIZONTAL.getHorizontalIndex()};

    for (int lengthIndex = 0; lengthIndex < ship.getShipLength(); lengthIndex++) {
      ShipLocation location = new ShipLocation();
      location.setShipCoordY(ship.getShipOriginY() + lengthIndex * indexMod[0]);
      location.setShipCoordX(ship.getShipOriginX() + lengthIndex * indexMod[1]);
      location.setShipNumber(ship.getShipNumber());
      location.setUserGame();
      shipLocations.add(location);
    }

  }
}
