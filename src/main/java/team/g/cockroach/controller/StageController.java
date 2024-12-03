package com.g_robot.controller.api;

import com.g_robot.dto.request.StageCreateRequestDto;
import com.g_robot.dto.response.StageResponseDto;
import com.g_robot.service.stage.StageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/stages")
@RequiredArgsConstructor
public class StageController {

	private final StageService stageService;

	/**
	 * ステージ一覧を取得
	 * 
	 * @param filter フィルタ条件（自分のステージ、人気のステージ、新しいステージ）
	 * @param page   ページ番号
	 * @return ステージ一覧
	 */
	@GetMapping
	public ResponseEntity<List<StageResponseDto>> findStages(
			@RequestParam(required = false, defaultValue = "all") String filter,
			@RequestParam(required = false, defaultValue = "0") int page,
			Principal principal) {
		List<StageResponseDto> stages = stageService.findStages(filter, page, principal.getName());
		return ResponseEntity.ok(stages);
	}

	/**
	 * 特定のステージを取得
	 * 
	 * @param stageId ステージID
	 * @return ステージ詳細
	 */
	@GetMapping("/{stageId}")
	public ResponseEntity<StageResponseDto> getStageDetails(
			@PathVariable String stageId) {
		StageResponseDto stage = stageService.getStageDetails(stageId);
		return ResponseEntity.ok(stage);
	}

	/**
	 * 新しいステージを作成
	 * 
	 * @param requestDto ステージ作成リクエスト
	 * @return 作成されたステージ
	 */
	@PostMapping("/create")
	public ResponseEntity<StageResponseDto> createStage(
			@Valid @RequestBody StageCreateRequestDto requestDto,
			Principal principal) {
		StageResponseDto createdStage = stageService.createStage(requestDto, principal.getName());
		return ResponseEntity.ok(createdStage);
	}

	/**
	 * ステージにいいねを付ける
	 * 
	 * @param stageId ステージID
	 * @return 更新後のステージ情報
	 */
	@PostMapping("/{stageId}/like")
	public ResponseEntity<StageResponseDto> likeStage(
			@PathVariable String stageId,
			Principal principal) {
		StageResponseDto updatedStage = stageService.likeStage(stageId, principal.getName());
		return ResponseEntity.ok(updatedStage);
	}

	/**
	 * ステージをプレイする
	 * 
	 * @param stageId ステージID
	 * @return ステージのプレイ情報
	 */
	@PostMapping("/{stageId}/play")
	public ResponseEntity<StageResponseDto> playStage(
			@PathVariable String stageId,
			Principal principal) {
		StageResponseDto playedStage = stageService.playStage(stageId, principal.getName());
		return ResponseEntity.ok(playedStage);
	}
}