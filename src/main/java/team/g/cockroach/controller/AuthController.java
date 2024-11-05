package team.g.cockroach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team.g.cockroach.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public String login(@RequestParam String userName, @RequestParam String password) {
		boolean isAuthenticated = authService.login(userName, password);
		if (isAuthenticated) {
			return "ログイン成功";
		} else {
			return "ログイン失敗";
		}
	}
    // 認証用のエンドポイントを追加
}