package edu.cnm.deepdive.cutthroatbattleshipservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Table(
    indexes = {
        @Index(columnList = "shot_id, timestamp")
    }
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({""})
public class Shot {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "shot_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JsonProperty(access = Access.READ_ONLY)
  private UserGame fromUser;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JsonProperty(access = Access.READ_ONLY)
  private UserGame toUser;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @JsonIgnore
  private Game game;

  @Column(nullable = false, updatable = true)
  @JsonProperty(access = Access.READ_WRITE)
  private int xCoord;

  @Column(nullable = false, updatable = true)
  @JsonProperty(access = Access.READ_WRITE)
  private int yCoord;

  @NonNull
  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  private Instant timestamp;

  @NonNull
  public Long getId() {
    return id;
  }

  @NonNull
  public Instant getTimestamp() {
    return timestamp;
  }

  @NonNull
  public UserGame getFromUser() {
    return fromUser;
  }

  public void setFromUser(@NonNull UserGame fromUser) {
    this.fromUser = fromUser;
  }

  @NonNull
  public UserGame getToUser() {
    return toUser;
  }

  public void setToUser(@NonNull UserGame toUser) {
    this.toUser = toUser;
  }

  @NonNull
  public Game getGame() {
    return game;
  }

  public void setGame(@NonNull Game game) {
    this.game = game;
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
