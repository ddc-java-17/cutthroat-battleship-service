package edu.cnm.deepdive.jata.model.dao;

import edu.cnm.deepdive.jata.model.entity.Game;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {


}
