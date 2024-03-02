package edu.cnm.deepdive.cutthroatbattleshipservice.model.dao;

import edu.cnm.deepdive.cutthroatbattleshipservice.model.entity.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetRepository extends JpaRepository<Fleet, Long> {

}
