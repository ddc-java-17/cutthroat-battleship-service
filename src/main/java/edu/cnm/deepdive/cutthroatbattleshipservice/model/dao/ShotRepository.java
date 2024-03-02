package edu.cnm.deepdive.cutthroatbattleshipservice.model.dao;

import edu.cnm.deepdive.cutthroatbattleshipservice.model.entity.Shot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShotRepository extends JpaRepository<Shot, Long> {


}
