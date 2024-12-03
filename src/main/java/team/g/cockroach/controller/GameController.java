package team.g.cockroach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team.g.cockroach.service.StageService;
import team.g.cockroach.dto.GameDTO;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private StageService gameStageService;

    /**
     * 指定されたIDのゲームを取得します。
     * 
     * @param id ゲームのID
     * @return ゲームの詳細情報
     */
    @GetMapping("/{id}")
    public GameDTO getGame(@PathVariable Long id) {
        return gameStageService.getGameById(id);
    }

    /**
     * 新しいゲームを作成します。
     * 
     * @param gameDTO 作成するゲームの情報
     */
    @PostMapping
    public void createGame(@RequestBody GameDTO gameDTO) {
        gameStageService.createGame(gameDTO);
    }

    /**
     * 指定されたIDのゲームを更新します。
     * 
     * @param id ゲームのID
     * @param gameDTO 更新するゲームの情報
     */
    @PutMapping("/{id}")
    public void updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        gameStageService.updateGame(id, gameDTO);
    }

    /**
     * 指定されたIDのゲームを削除します。
     * 
     * @param id ゲームのID
     */
    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameStageService.deleteGame(id);
    }
}
