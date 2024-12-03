package team.g.cockroach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team.g.cockroach.service.AuthService;
import team.g.cockroach.dto.AuthRequestDTO;
import team.g.cockroach.dto.SignupRequestDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public String login(@RequestBody AuthRequestDTO authRequest) {
		boolean isAuthenticated = authService.login(authRequest.getUsername(), authRequest.getPassword());
		if (isAuthenticated) {
			return "ログイン成功";
		} else {
			return "ログイン失敗";
		}
	}

	@PostMapping("/signup")
	public String signup(@RequestBody SignupRequestDTO signupRequest) {
		boolean isRegistered = authService.signup(signupRequest.getUsername(), signupRequest.getPassword(), signupRequest.getEmail());
		if (isRegistered) {
			return "アカウント作成成功";
		} else {
			return "アカウント作成失敗";
		}
	}
}