package team.g.cockroach.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/stages")
public class StageController {
	private final StageService stageService;

	@GetMapping
	public Page<StageCardResponse> getStages(
			@RequestParam(defaultValue = "all") String filter,
			@RequestParam(required = false) String userId,
			@PageableDefault(size = 10) Pageable pageable) {
		return stageService.findStages(filter, userId, pageable);
	}

	@GetMapping("/{stageId}")
	public StageDetailResponse getStage(@PathVariable String stageId) {
		return stageService.findStageById(stageId);
	}
}