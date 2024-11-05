package team.g.cockroach.dto;

public class UserDTO {
    private String username;
    private String email;

    // ゲッターとセッターを追加
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
