package team.g.cockroach.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import team.g.cockroach.entity.GameStage;

public interface StageRepository extends JpaRepository<GameStage, UUID> {
    Page<GameStage> findByCreatorId(Long creatorId, Pageable pageable);
}