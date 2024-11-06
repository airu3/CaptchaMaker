package team.g.cockroach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team.g.cockroach.entity.GameStage;
import team.g.cockroach.repository.StageRepository;

@Service
public class GameStageService {

	@Autowired
	private StageRepository stageRepository;

	public Page<GameStage> findAllGameStages(PageRequest pageRequest) {
		return stageRepository.findAll(pageRequest);
	}

	public Page<GameStage> findMyGameStages(PageRequest pageRequest) {
		// 自分のステージを取得するロジックを実装
		return stageRepository.findByCreatorId(getCurrentUserId(), pageRequest);
	}

	public Page<GameStage> findPopularGameStages(PageRequest pageRequest) {
		return stageRepository.findAll(PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(),
				Sort.by(Sort.Direction.DESC, "likes", "playCount")));
	}

	public Page<GameStage> findNewGameStages(PageRequest pageRequest) {
		return stageRepository.findAll(PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(),
				Sort.by(Sort.Direction.DESC, "createdDate")));
	}

	private Long getCurrentUserId() {
		// 現在のユーザーIDを取得するロジックを実装
		return 1L; // 仮のユーザーID
	}
}