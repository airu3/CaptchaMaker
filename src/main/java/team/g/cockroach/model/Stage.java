package team.g.cockroach.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
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

	// Getters and Setters
}