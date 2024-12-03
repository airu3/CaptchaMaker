package team.g.cockroach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.g.cockroach.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// Additional query methods can be defined here
}