package edu.cnm.deepdive.cutthroatbattleshipservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "user_game", indexes = {
    @Index(columnList = "user_game_id, user_id, game_id"),
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
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private List<User> user = new LinkedList<>();

  @Id
  @NonNull
  @OneToOne(optional = false, fetch = FetchType.EAGER)
  @JsonProperty(access = Access.READ_ONLY)
  private Game game;

  @NonNull
  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JsonProperty(access = Access.READ_ONLY)
  private Fleet fleet;

  @NonNull
  @ManyToMany(fetch = FetchType.EAGER)
  @JsonProperty(access = Access.READ_ONLY)
  private List<Shot> shot = new LinkedList<>();

  @NonNull
  public Long getId() {
    return id;
  }

  @NonNull
  public List<User> getUser() {
    return user;
  }

  public void setUser(@NonNull List<User> user) {
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

  @NonNull
  public List<Shot> getShot() {
    return shot;
  }

  public void setShot(@NonNull List<Shot> shot) {
    this.shot = shot;
  }

  // TODO: 3/2/2024 Figure out methods this entity needs.
  // TOTO: I BLESS THE RAINS DOWN IN AAAFRICAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
}
