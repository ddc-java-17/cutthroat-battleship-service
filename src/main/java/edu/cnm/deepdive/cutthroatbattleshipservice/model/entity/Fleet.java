package edu.cnm.deepdive.cutthroatbattleshipservice.model.entity;

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
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.util.LinkedList;
import java.util.List;
import org.springframework.lang.NonNull;

@Entity
@Table(indexes = @Index(columnList = "fleet_id"))
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({""})
public class Fleet {

  @NonNull
  @Id
  @Column(name = "fleet_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @NonNull
  @OneToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private User user; // TODO: 2/29/2024 Import class when Danielle finished

  @NonNull
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Game game; // TODO: 2/29/2024 Import class when WYatt finished

  @NonNull
  @OneToMany(mappedBy = "fleet", fetch = FetchType.EAGER,
      cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonProperty(access = Access.READ_ONLY)
  private final List<Ship> ships = new LinkedList<>();
}
