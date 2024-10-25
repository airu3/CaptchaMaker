package team.g.cockroach.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // 追加
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String imageUrl;
	private LocalDateTime createdDate;
	private int likes;
	private int playCount;
	private double bestTime;
	private double clearRate;
	private Long creatorId;
}