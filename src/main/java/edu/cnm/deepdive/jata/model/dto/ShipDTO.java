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
}
