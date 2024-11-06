package team.g.cockroach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.g.cockroach.entity.GameStage;
import team.g.cockroach.service.GameStageService;

@Controller
public class StageController {

	@Autowired
	private GameStageService gameStageService;

	@GetMapping("/stages")
	public String listStages(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "all") String filter,
			Model model) {

		Page<GameStage> stages;
		switch (filter) {
			case "my":
				stages = gameStageService.findMyStages(PageRequest.of(page, size));
				break;
			case "popular":
				stages = gameStageService.findPopularStages(PageRequest.of(page, size));
				break;
			case "new":
				stages = gameStageService.findNewStages(PageRequest.of(page, size));
				break;
			default:
				stages = gameStageService.findAllStages(PageRequest.of(page, size));
				break;
		}

		model.addAttribute("stages", stages.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", stages.getTotalPages());
		model.addAttribute("filter", filter);

		return "stages";
	}

	@GetMapping("/create")
	public String createStage() {
		return "create";
	}
}