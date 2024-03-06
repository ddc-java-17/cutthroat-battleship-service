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
  @JsonProperty(access = Access.READ_ONLY)
  private Game game;

  @NonNull
  @OneToOne(mappedBy = "userGame",
      optional = false,
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @JsonProperty(access = Access.READ_ONLY)
  private Fleet fleet;

  @NonNull
  public Long getId() {
    return id;
  }

  @NonNull
  public User getUser() {
    return user;
  }

  public void setUser(@NonNull User user) {
    this.user = user;
  }

  @NonNull
  public Game getGame() {
    return game;
  }

  public void setGame(@NonNull Game game) {
    this.game = game;
  }

  @NonNull
  public Fleet getFleet() {
    return fleet;
  }

  public void setFleet(Fleet fleet) {
    this.fleet = fleet;
  }

  // TODO: 3/2/2024 Figure out methods this entity needs.
}
