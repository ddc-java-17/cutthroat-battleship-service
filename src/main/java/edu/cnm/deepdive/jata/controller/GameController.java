package edu.cnm.deepdive.jata.controller;

import edu.cnm.deepdive.jata.model.dto.GameDTO;
import edu.cnm.deepdive.jata.model.entity.Game;
import edu.cnm.deepdive.jata.service.AbstractGameService;
import edu.cnm.deepdive.jata.service.AbstractUserService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * This is a REST controller that makes and sends HTTP requests to and from the cloud.
 */
@RestController
@RequestMapping("/games")
public class GameController {

  public static final long POLL_TIMEOUT_VALUE = 30000L;
  public static final int SHORT_POLL_TIMEOUT = 5000;
  private final AbstractUserService userService;
  private final AbstractGameService gameService;

  /**
   * this is the constructor for the instance of the controller
   * @param userService
   * @param gameService
   */
  public GameController(AbstractUserService userService, AbstractGameService gameService) {
    this.userService = userService;
    this.gameService = gameService;
  }

  /**
   *  This is an endpoint that looks for the creation of a game.
   * @param game
   * @return
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GameDTO> post(@Valid @RequestBody Game game) {
    GameDTO createdGame = gameService.startJoinGame(game, userService.getCurrentUser());
    URI location = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(getClass())
            .get(createdGame.getKey())).toUri();
    return ResponseEntity.created(location).body(createdGame);
  }

  /**
   * This endpoint gets a created game. The game key is embedded in the path.
   * This uses a long poll to search for state changes (turnCount changed) and returns the
   * current game.  This will also allow a user to rejoin a game in progress.
   * @param gameKey
   * @return
   */

  private ExecutorService referees = Executors.newCachedThreadPool();


  @GetMapping(path = "/{gameKey}", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<GameDTO> get(@PathVariable UUID gameKey){
    DeferredResult<GameDTO> getGameDTO = new DeferredResult<>(POLL_TIMEOUT_VALUE);
    referees.execute(() ->
    {
      try {
        long turnCounter = gameService.getTurnCount(gameKey, userService.getCurrentUser());
        getGameDTO.onTimeout(()-> {
          getGameDTO.setResult(gameService.getGame(gameKey, userService.getCurrentUser()));
        });
        while (true) {
          Thread.sleep(SHORT_POLL_TIMEOUT);
          if(gameService.getTurnCount(gameKey, userService.getCurrentUser()) != turnCounter){
            getGameDTO.setResult(gameService.getGame(gameKey, userService.getCurrentUser()));
            break;
          }
        }
      } catch (InterruptedException e) {
        // no action needed
      }
    });
    return getGameDTO;
  }

}
