package edu.cnm.deepdive.jata.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import edu.cnm.deepdive.jata.model.Direction;
import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.service.InvalidShipLocationException;
import jakarta.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ShipDTO {

  public static final int MIN_SHIP_LENGTH = 2;

  @JsonProperty(access = Access.READ_WRITE)
  private int shipNumber;

  @JsonProperty(access = Access.READ_WRITE)
  @JsonUnwrapped
  private Location origin;

  @JsonProperty(access = Access.READ_WRITE)
  @Min(MIN_SHIP_LENGTH)
  private int length;

  @JsonProperty(access = Access.READ_WRITE)
  private boolean vertical;

  public int getShipNumber() {
    return shipNumber;
  }

  public void setShipNumber(int shipNumber) {
    this.shipNumber = shipNumber;
  }

  public Location getOrigin() {
    return origin;
  }

  public void setOrigin(Location origin) {
    this.origin = origin;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public boolean isVertical() {
    return vertical;
  }

  public void setVertical(boolean vertical) {
    this.vertical = vertical;
  }

  public Stream<ShipLocation> tovalidshiplocations(int boardSize, int shipNumber) {
    int offsetX;
    int offsetY;

    if (vertical) {
      offsetX = Direction.VERTICAL.getVerticalIndex();
      offsetY = Direction.VERTICAL.getHorizontalIndex();
    } else {
      offsetX = Direction.HORIZONTAL.getVerticalIndex();
      offsetY = Direction.HORIZONTAL.getHorizontalIndex();
    }
    ValidateBoardEdge(boardSize);

    return IntStream.range(0, length)
        .mapToObj((index) -> {
          Location loc = new Location((origin.getX() + index * offsetX),
              (origin.getX() + index * offsetY));
          ShipLocation sl = new ShipLocation();
          sl.setLocation(loc);
          return sl;
        });
  }

  private void ValidateBoardEdge(int boardSize) {
    if (((origin.getX() + length) > boardSize)
        || ((origin.getY() + length) > boardSize)) {
      throw new InvalidShipLocationException("Ships must be placed on the board");
    }
  }

  public static List<ShipDTO> fromLocations(List<ShipLocation> locations){
    return locations.stream()
        .collect(Collectors.groupingBy(ShipLocation::getShipNumber))
        .values()
        .stream()
        .map(ShipDTO::toShipDTO)
        .collect(Collectors.toList());
  }


  private static ShipDTO toShipDTO(List<ShipLocation> locations) {
    return null;
  }
}
