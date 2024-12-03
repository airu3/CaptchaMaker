package team.g.cockroach.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StageService {
	private final StageRepository stageRepository;
	private final StageImageRepository stageImageRepository;
	private final UserStageActionRepository userStageActionRepository;

	public Page<StageCardResponse> findStages(
			String filter,
			String userId,
			Pageable pageable) {
		Page<Stage> stages;

		switch (filter) {
			case "popular":
				stages = stageRepository.findAllByOrderByLikesDesc(pageable);
				break;
			case "new":
				stages = stageRepository.findAllByOrderByCreatedAtDesc(pageable);
				break;
			case "my":
				stages = stageRepository.findAllByUserId(userId, pageable);
				break;
			default:
				stages = stageRepository.findAll(pageable);
		}

		return stages.map(this::toStageCardResponse);
	}

	public StageDetailResponse findStageById(String stageId) {
		Stage stage = stageRepository.findById(stageId)
				.orElseThrow(() -> new NotFoundException("Stage not found"));

		List<StageImage> images = stageImageRepository.findByStageIdOrderBySequenceNo(stageId);

		return toStageDetailResponse(stage, images);
	}

	private StageCardResponse toStageCardResponse(Stage stage) {
		StageCardResponse response = new StageCardResponse();
		// マッピング処理
		return response;
	}

	private StageDetailResponse toStageDetailResponse(Stage stage, List<StageImage> images) {
		StageDetailResponse response = new StageDetailResponse();
		// マッピング処理
		return response;
	}
}