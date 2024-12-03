package team.g.cockroach.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stages")
@Data
public class Stage {
	@Id
	private String id;
	private String userId;
	private String stageName;
	private String stageQuestion;
	private String questionDetails;
	private String authorComment;
	private int timer;
	private int likes;
	private int playCount;
	private LocalDateTime createdAt;
}