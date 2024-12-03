package com.g_robot.controller.api;

import com.g_robot.dto.response.UserProfileResponseDto;
import com.g_robot.dto.response.StageResponseDto;
import com.g_robot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	/**
	 * ユーザープロフィールを取得
	 * 
	 * @param userId ユーザーID（指定がない場合は現在ログインしているユーザー）
	 * @return ユーザープロフィール情報
	 */
	@GetMapping("/profile")
	public ResponseEntity<UserProfileResponseDto> getUserProfile(
			@RequestParam(required = false) String userId,
			Principal principal) {
		String targetUserId = userId != null ? userId : principal.getName();
		UserProfileResponseDto profile = userService.getUserProfile(targetUserId);
		return ResponseEntity.ok(profile);
	}

	/**
	 * ユーザーが作成したステージ一覧を取得
	 * 
	 * @param userId ユーザーID
	 * @param page   ページ番号
	 * @return ステージ一覧
	 */
	@GetMapping("/{userId}/stages")
	public ResponseEntity<List<StageResponseDto>> getUserStages(
			@PathVariable String userId,
			@RequestParam(required = false, defaultValue = "0") int page) {
		List<StageResponseDto> stages = userService.getUserStages(userId, page);
		return ResponseEntity.ok(stages);
	}
}