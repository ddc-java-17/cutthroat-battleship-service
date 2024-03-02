package edu.cnm.deepdive.cutthroatbattleshipservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import org.springframework.lang.NonNull;

@Entity
@Table(indexes = @Index(columnList = "ship_id"))
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({""})
public class Ship {

  @NonNull
  @Id
  @Column(name = "ship_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private int fleet;

  @Column(nullable = false, updatable = true)
  @JsonProperty(access = Access.READ_WRITE)
  private int xCoord;

  @Column(nullable = false, updatable = true)
  @JsonProperty(access = Access.READ_WRITE)
  private int yCoord;

  @NonNull
  public Long getId() {
    return id;
  }

  public int getFleet() {
    return fleet;
  }

  public int getxCoord() {
    return xCoord;
  }

  public void setxCoord(int xCoord) {
    this.xCoord = xCoord;
  }

  public int getyCoord() {
    return yCoord;
  }

  public void setyCoord(int yCoord) {
    this.yCoord = yCoord;
  }
}
