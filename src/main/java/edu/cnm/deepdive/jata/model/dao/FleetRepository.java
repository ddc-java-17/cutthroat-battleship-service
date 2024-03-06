package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.Fleet;
import edu.cnm.deepdive.jata.model.entity.Ship;
import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetRepository extends JpaRepository<Fleet, Long> {

  Optional<Fleet> findFleetByUserGame(UserGame userGame);

}
