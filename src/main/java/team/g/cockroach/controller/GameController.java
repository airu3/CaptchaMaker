package team.g.cockroach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team.g.cockroach.service.GameService;
import team.g.cockroach.dto.GameDTO;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/{id}")
    public GameDTO getGame(@PathVariable Long id) {
        return gameService.getGameById(id);
    }

    @PostMapping
    public void createGame(@RequestBody GameDTO gameDTO) {
        gameService.createGame(gameDTO);
    }

    @PutMapping("/{id}")
    public void updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        gameService.updateGame(id, gameDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }
}
