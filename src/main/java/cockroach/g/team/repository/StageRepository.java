package cockroach.g.team.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cockroach.g.team.model.Stage;

public interface StageRepository extends JpaRepository<Stage, Long> {
    Page<Stage> findByCreatorId(Long creatorId, Pageable pageable);
}