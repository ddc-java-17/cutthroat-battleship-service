package edu.cnm.deepdive.cutthroatbattleshipservice.model.entity;

import androidx.room.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity(
    tableName = "user_game",
    foreignKeys =
        {@ForeignKey(
            entity = User.class,
            childColumns = "user_id",
            parentColumns = "user_id"),
        @ForeignKey(
            entity = Game.class,
            childColumns = "game_id",
            parentColumns = "game_id")
        }
)

public class UserGame {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "user_game_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @NonNull
  @ManyToMany(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private User user;

  @Id
  @NonNull
  @OneToOne(optional = false, fetch = FetchType.EAGER)
  @ColumnInfo(name = "game_id", nullable = false, index = true)
  @JsonProperty(access = Access.READ_ONLY)
  private Game game;

  @NonNull
  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JsonProperty(access = Access.READ_ONLY)
  private Fleet fleet;

  @NonNull
  @ManyToMany(optional = false, fetch = FetchType.EAGER)
  @JsonProperty(access = Access.READ_ONLY)
  private Shot shot;

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

  @NonNull
  public Shot getShot() {
    return shot;
  }

  public void setShot(@NonNull Shot shot) {
    this.shot = shot;
  }

  // TODO: 3/2/2024 Figure out methods this entity needs.
  // TOTO: I BLESS THE RAINS DOWN IN AAAFRICAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
}
