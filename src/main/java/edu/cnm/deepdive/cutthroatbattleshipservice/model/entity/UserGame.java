package edu.cnm.deepdive.cutthroatbattleshipservice.model.entity;

import androidx.room.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
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
    foreignKeys = @ForeignKey(
        entity = User.class,
        childColumns = "user_id",
        parentColumns = "user_id"
    )
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

  @ColumnInfo(name = "game_id", index = true)
  private long gameId;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  private Fleet<List<Ship>> fleet;
  // TODO: 3/1/2024 Fleet

  @ManyToMany
  // TODO: 3/1/2024 Shot

}
