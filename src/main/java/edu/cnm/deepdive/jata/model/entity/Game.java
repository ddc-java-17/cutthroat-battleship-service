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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Table(indexes = @Index(columnList = "game_id"))
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({""})
public class Game {

  @NonNull
  @Id
  @GeneratedValue
  @Column(name = "game_id", nullable = false, updatable = false)
  @JsonIgnore
  private long id;

  @NonNull
  @Column(name = "external_key", nullable = false, updatable = false, unique = true, columnDefinition = "UUID")
  @JsonProperty(access = Access.READ_ONLY)
  private UUID key;

  @NonNull
  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonProperty(access = Access.READ_ONLY)
  private Instant created;

  @NonNull
  @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
  @JsonProperty(access = Access.READ_ONLY)
  private final List<UserGame> userGame = new LinkedList<>();

  @NonNull
  @OneToMany(mappedBy = "game", fetch = FetchType.EAGER,
      cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonProperty(access = Access.READ_ONLY)
  private final List<Fleet> fleets = new LinkedList<>();

  @NonNull
  @JsonProperty(access = Access.READ_WRITE)
  private String boardPool;

  @NonNull
  @JsonProperty(access = Access.READ_ONLY)
  private int playerCount;

  @NonNull
  @OneToMany
  @JsonProperty(access = Access.READ_ONLY)
  private final List<Shot> shots = new LinkedList<>();

  public long getId() {
    return id;
  }

  @NonNull
  public UUID getKey() {
    return key;
  }

  @NonNull
  public Instant getCreated() {
    return created;
  }

  @NonNull
  public List<UserGame> getUserGame() {
    return userGame;
  }

  @NonNull
  public List<Fleet> getFleets() {
    return fleets;
  }

 @NonNull
  public List<Shot> getShots() {
    return shots;
  }

  @NonNull
  public String getBoardPool() {
    return boardPool;
  }

  public void setBoardPool(@NonNull String boardPool) {
    this.boardPool = boardPool;
  }

  public int getPlayerCount() {
    return playerCount;
  }

}
