package team.g.cockroach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import team.g.cockroach.model.Stage;
import team.g.cockroach.service.StageService;

@Controller
public class StageController {

	@Autowired
	private StageService stageService;

	@GetMapping("/stages")
	public String listStages(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "all") String filter,
			Model model) {

		Page<Stage> stages;
		switch (filter) {
			case "my":
				stages = stageService.findMyStages(PageRequest.of(page, size));
				break;
			case "popular":
				stages = stageService.findPopularStages(PageRequest.of(page, size));
				break;
			case "new":
				stages = stageService.findNewStages(PageRequest.of(page, size));
				break;
			default:
				stages = stageService.findAllStages(PageRequest.of(page, size));
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