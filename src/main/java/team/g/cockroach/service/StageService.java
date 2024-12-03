package team.g.cockroach.service.stage;

import team.g.cockroach.dto.request.StageCreateRequestDto;
import team.g.cockroach.dto.request.StageImageUploadDto;
import team.g.cockroach.dto.response.StageResponseDto;
import team.g.cockroach.model.Stage;
import team.g.cockroach.model.StageImage;
import team.g.cockroach.repository.StageRepository;
import team.g.cockroach.repository.StageImageRepository;
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
@RequiredArgsConstructor
public class StageService {

	private final StageRepository stageRepository;
	private final StageImageRepository stageImageRepository;

	private static final String UPLOAD_DIR = "uploads/stages/";

	@Transactional
	public StageResponseDto createStage(StageCreateRequestDto request) {
		Stage stage = new Stage();
		stage.setStageName(request.getStageName());
		stage.setStageQuestion(request.getStageQuestion());
		stage.setQuestionDetails(request.getQuestionDetails());
		stage.setAuthorComment(request.getAuthorComment());
		stage.setTimer(request.getTimer());

		Stage savedStage = stageRepository.save(stage);
		return convertToResponseDto(savedStage);
	}

	@Transactional
	public StageResponseDto uploadStageImage(
			String stageId,
			StageImageUploadDto imageUploadDto,
			MultipartFile imageFile) {

		String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
		Path filePath = Paths.get(UPLOAD_DIR, fileName);

		try {
			Files.createDirectories(filePath.getParent());
			Files.write(filePath, imageFile.getBytes());

			StageImage stageImage = new StageImage();
			stageImage.setStageId(stageId);
			stageImage.setSequenceNo(imageUploadDto.getSequenceNo());
			stageImage.setImagePath(fileName);
			stageImage.setIsCorrect(imageUploadDto.getIsCorrect());

			stageImageRepository.save(stageImage);

			return getStageDetails(stageId);
		} catch (IOException e) {
			throw new RuntimeException("画像のアップロードに失敗しました", e);
		}
	}

	public List<StageResponseDto> findStages(String filter) {
		List<Stage> stages = switch (filter) {
			case "popular" -> stageRepository.findTop10ByOrderByLikesDesc();
			case "recent" -> stageRepository.findTop10ByOrderByCreatedAtDesc();
			default -> stageRepository.findAll();
		};

		return stages.stream()
				.map(this::convertToResponseDto)
				.collect(Collectors.toList());
	}

	public StageResponseDto getStageDetails(String stageId) {
		Stage stage = stageRepository.findById(stageId)
				.orElseThrow(() -> new RuntimeException("ステージが見つかりません"));

		List<StageImage> images = stageImageRepository.findByStageId(stageId);

		StageResponseDto responseDto = convertToResponseDto(stage);
		responseDto.setImages(images.stream()
				.map(this::convertToImageResponseDto)
				.collect(Collectors.toList()));

		return responseDto;
	}

	@Transactional
	public StageResponseDto updateStage(String stageId, StageCreateRequestDto request) {
		Stage stage = stageRepository.findById(stageId)
				.orElseThrow(() -> new RuntimeException("ステージが見つかりません"));

		stage.setStageName(request.getStageName());
		stage.setStageQuestion(request.getStageQuestion());
		stage.setQuestionDetails(request.getQuestionDetails());
		stage.setAuthorComment(request.getAuthorComment());
		stage.setTimer(request.getTimer());

		Stage updatedStage = stageRepository.save(stage);
		return convertToResponseDto(updatedStage);
	}

	private StageResponseDto convertToResponseDto(Stage stage) {
		StageResponseDto dto = new StageResponseDto();
		dto.setStageId(stage.getStageId());
		dto.setStageName(stage.getStageName());
		dto.setStageQuestion(stage.getStageQuestion());
		dto.setQuestionDetails(stage.getQuestionDetails());
		dto.setAuthorComment(stage.getAuthorComment());
		dto.setTimer(stage.getTimer());
		dto.setLikes(stage.getLikes());
		dto.setPlayCount(stage.getPlayCount());
		return dto;
	}

	private StageImageResponseDto convertToImageResponseDto(StageImage image) {
		StageImageResponseDto dto = new StageImageResponseDto();
		dto.setImagePath(image.getImagePath());
		dto.setSequenceNo(image.getSequenceNo());
		dto.setIsCorrect(image.getIsCorrect());
		return dto;
	}
}