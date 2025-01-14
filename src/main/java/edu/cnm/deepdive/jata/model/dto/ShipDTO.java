package edu.cnm.deepdive.jata.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import edu.cnm.deepdive.jata.model.Direction;
import edu.cnm.deepdive.jata.model.Location;
import edu.cnm.deepdive.jata.model.entity.ShipLocation;
import edu.cnm.deepdive.jata.service.InvalidShipLocationException;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class acts as an interface between the user and the server.  It implements the translation
 * between the vector definition of a ship that the user has and the cartesian definition
 * that the server uses.  This translation acts both ways.  Further, validation of ships
 * against the far board edge, and against ship intersections, is performed on the inbound ships.
 */
public class ShipDTO {

  private static final int MIN_SHIP_LENGTH = 2;
  private static final int MAX_SHIP_LENGTH = 8;

  @JsonProperty(access = Access.READ_WRITE)
  @JsonUnwrapped
  private Location origin;

  @JsonProperty(access = Access.READ_WRITE)
  @Min(MIN_SHIP_LENGTH)
  private int length;

  @JsonProperty(access = Access.READ_WRITE)
  private boolean vertical;

  /**
   * Returns the origin of the ship.  THis is an x-y coordinate indicating the origin of
   * the vector that represents the ship in user terms.
   * @return
   */
  public Location getOrigin() {
    return origin;
  }

  /**
   * Annotates the origin of a ship
   * @param origin
   */
  public void setOrigin(Location origin) {
    this.origin = origin;
  }

  /**
   * Returns the length of a ship.  This is the total length including the origin.  All
   * values are positive!
   * @return
   */
  public int getLength() {
    return length;
  }

  /**
   * Annotates the length of a ship
   * @param length
   */
  public void setLength(int length) {
    this.length = length;
  }

  /**
   * Returns the value of the vertical flag.  If the vertical flag is set, the ship occupies
   * one column and several rows.  If the vertical flag is not set, the ship occupies
   * one row and several columns
   *
   * @return
   */
  public boolean isVertical() {
    return vertical;
  }

  /**
   * Annotates the value of the vertical flag
   * @param vertical
   */
  public void setVertical(boolean vertical) {
    this.vertical = vertical;
  }

  /**
   * This method validates incoming ships and, once validated, converts them from the
   * vector format the users have to the cartesian format the server uses.
   *
   * @param boardSize
   * @param shipNumber
   * @return
   */
  public Stream<ShipLocation> tovalidshiplocations(int boardSize, int shipNumber) {
    int[] offset;

    if (vertical) {
      offset = new int[]{Direction.VERTICAL.getVerticalIndex(),
          Direction.VERTICAL.getHorizontalIndex()};
    } else {
      offset = new int[]{Direction.HORIZONTAL.getVerticalIndex(),
          Direction.HORIZONTAL.getHorizontalIndex()};
    }
    ValidateBoardEdge(boardSize);

    return IntStream.range(0, length)
        .mapToObj((index) -> {
          Location loc = new Location((origin.getX() + index * offset[1]),
              (origin.getY() + index * offset[0]));
          ShipLocation sl = new ShipLocation();
          sl.setLocation(loc);
          sl.setShipNumber(shipNumber);
          return sl;
        });
  }

  /**
   * This method validates each ship against the far edge of board.  Board size is determined
   * by the bard size enum.  Verticality is used to determine which edge to check against.
   * @param boardSize
   */
  private void ValidateBoardEdge(int boardSize) {
    if ((!vertical && (origin.getX() + length-1) > boardSize)
        || (vertical && (origin.getY() + length-1) > boardSize)) {
      throw new InvalidShipLocationException("Ships must be placed on the board");
    }
  }

  /**
   * This method takes ships in cartesian format from the server and translates them into vector
   * format for transmiossion to the user.
   *
   * @param locations
   * @return
   */
  public static List<ShipDTO> fromLocations(List<ShipLocation> locations) {
    return locations.stream()
        .collect(Collectors.groupingBy(ShipLocation::getShipNumber))
        .values()
        .stream()
        .map(ShipDTO::toShipDTO)
        .collect(Collectors.toList());
  }


  /**
   * This method implements the cartesian-to-vector mathematics
   * @param locations
   * @return
   */
  private static ShipDTO toShipDTO(List<ShipLocation> locations) {
    ShipDTO shipDTO = new ShipDTO();
    Location origin = new Location();
    int minX = MAX_SHIP_LENGTH;
    int maxX = 0;
    int minY = MAX_SHIP_LENGTH;
    int maxY = 0;

    for (ShipLocation location : locations) {
      if (location.getLocation().getX() > maxX) {
        maxX = location.getLocation().getX();
      }
      if (location.getLocation().getX() < minX) {
        minX = location.getLocation().getX();
      }
      if (location.getLocation().getY() > maxY) {
        maxY = location.getLocation().getY();
      }
      if (location.getLocation().getY() < minY) {
        minY = location.getLocation().getY();
      }
    }
    shipDTO.setVertical((maxX - minX) == 0);
    shipDTO.setLength((shipDTO.isVertical()) ? (maxY - minY)+1 : (maxX - minX)+1);
    origin.setX(minX);
    origin.setY(minY);
    shipDTO.setOrigin(origin);
    return shipDTO;
  }
}
