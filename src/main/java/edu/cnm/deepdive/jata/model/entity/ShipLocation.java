package edu.cnm.deepdive.jata.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.lang.NonNull;

/**
 * This class contains a single ship of some type.  Each row represents a single point on the ship.  Ships size is determined by the number of rows in the table
 */
@Entity
@Table(indexes = @Index(columnList = "ship_id"))
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({""})
public class ShipLocation {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "ship_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JsonProperty(access = Access.READ_ONLY)
  private UserGame userGame;

  @Column(nullable = false, updatable = true)
  @JsonProperty(access = Access.READ_WRITE)
  private int shipNumber;

  @Column(nullable = false, updatable = true)
  @JsonProperty(access = Access.READ_WRITE)
  private int xCoord;

  @Column(nullable = false, updatable = true)
  @JsonProperty(access = Access.READ_WRITE)
  private int yCoord;

  /**
   * Returns the ID of the ship
   *
   * @return
   */
  @NonNull
  public Long getId() {
    return id;
  }

  /**
   * Returns the userGame of a particular ship
   *
   * @return
   */
  @NonNull
  public UserGame getUserGame() {
    return userGame;
  }

  /**
   * Annotates this ship with its associated user via UserGame
   * @param userGame
   */
  public void setUserGame(@NonNull UserGame userGame) {
    this.userGame = userGame;
  }

  /**
   * Returns the ship number (identifier) for a particular ship belonging to a userGame
   *
   * @return
   */
  public int getShipNumber() {
    return shipNumber;
  }

  /**
   * Annotates the ship number (identifier) for a particular ship belonging to a userGame
   *
   * @param shipNumber
   */
  public void setShipNumber(int shipNumber) {
    this.shipNumber = shipNumber;
  }

  /**
   * Returns the x-coordinate of a single point on the ship
   *
   * @return
   */
  public int getxCoord() {
    return xCoord;
  }

  /**
   * Annotates the x-coordinate of a single point on the ship
   *
   * @param xCoord
   */
  public void setxCoord(int xCoord) {
    this.xCoord = xCoord;
  }

  /**
   * Returns the y-coordinate of a single point on the ship
   *
   * @return
   */
  public int getyCoord() {
    return yCoord;
  }

  /**
   * Annotates the y-coordinate of a single point on the ship
   *
   * @param yCoord
   */
  public void setyCoord(int yCoord) {
    this.yCoord = yCoord;
  }
}
