package team.g.cockroach.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import team.g.cockroach.domain.Stage;

import java.util.UUID;

public interface StageRepository extends JpaRepository<Stage, UUID> {
    Page<Stage> findByCreatorId(Long creatorId, Pageable pageable);
}