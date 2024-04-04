package edu.cnm.deepdive.jata.model.dto;

import edu.cnm.deepdive.jata.model.entity.UserGame;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class GameDTO {


  private UUID key;

  private final List<UserGame> userGames = new LinkedList<>();

  private int boardSize;

  private int playerCount;

  private boolean started;

  private boolean finished;


}
