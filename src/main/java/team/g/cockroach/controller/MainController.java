package team.g.cockroach.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

	@GetMapping("/")
	public String index() {
		return "redirect:/login.html";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@GetMapping("/title")
	public String title() {
		return "title";
	}

	@GetMapping("/stage/find")
	public String findStage() {
		return "stage/find";
	}

	@GetMapping("/stage/{stageId}")
	public String playStage(@PathVariable String stageId, Model model) {
		model.addAttribute("stageId", stageId);
		return "stage/play";
	}

	@GetMapping("/stage/create")
	public String createStage() {
		return "stage/create";
	}
}