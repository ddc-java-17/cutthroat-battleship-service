package edu.cnm.deepdive.jata.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.lang.NonNull;

/**
 * This class is the central hub of communication. All information the user needs comes through
 * this class.
 */

@Entity
@Table(name = "user_game", indexes = {
    @Index(columnList = "user_game_id, user_id"),
})
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"user_game_id"})
public class UserGame {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "user_game_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private User user;

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Game game;

  /**
   * Gets this UserGame's identifying number
   * @return id UserGame identification
   */
  @NonNull
  public Long getId() {
    return id;
  }

  /**
   * Gets the user object associated with this UserGame
   * @return user User object
   */
  @NonNull
  public User getUser() {
    return user;
  }

  /**
   * Sets the user object associated with this UserGame
   * @param user  User object.
   */
  public void setUser(@NonNull User user) {
    this.user = user;
  }

  /**
   * Gets the game object associated with this UserGame
   * @return game Game object
   */
  @NonNull
  public Game getGame() {
    return game;
  }

  /**
   * Sets the game associated to this UserGame
   * @param game  Game object
   */
  public void setGame(@NonNull Game game) {
    this.game = game;
  }

}
