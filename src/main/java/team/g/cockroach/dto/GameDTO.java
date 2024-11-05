package team.g.cockroach.dto;

public class GameDTO {
    private String gameName;
    private String gameType;

    // ゲッターとセッターを追加
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
