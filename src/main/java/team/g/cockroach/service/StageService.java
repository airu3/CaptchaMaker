package team.g.cockroach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team.g.cockroach.model.Stage;
import team.g.cockroach.repository.StageRepository;

@Service
public class StageService {

	@Autowired
	private StageRepository stageRepository;

	public Page<Stage> findAllStages(PageRequest pageRequest) {
		return stageRepository.findAll(pageRequest);
	}

	public Page<Stage> findMyStages(PageRequest pageRequest) {
		// 自分のステージを取得するロジックを実装
		return stageRepository.findByCreatorId(getCurrentUserId(), pageRequest);
	}

	public Page<Stage> findPopularStages(PageRequest pageRequest) {
		return stageRepository.findAll(PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(),
				Sort.by(Sort.Direction.DESC, "likes", "playCount")));
	}

	public Page<Stage> findNewStages(PageRequest pageRequest) {
		return stageRepository.findAll(PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(),
				Sort.by(Sort.Direction.DESC, "createdDate")));
	}

	private Long getCurrentUserId() {
		// 現在のユーザーIDを取得するロジックを実装
		return 1L; // 仮のユーザーID
	}
}