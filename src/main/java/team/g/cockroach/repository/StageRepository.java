package team.g.cockroach.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import team.g.cockroach.model.Stage;

public interface StageRepository extends JpaRepository<Stage, Long> {
    Page<Stage> findByCreatorId(Long creatorId, Pageable pageable);
}