package team.g.cockroach.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GameStage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID stageId; // UUIDを使用して一意のステージを識別

	private String stageName;
	private String stageQuestion;
	private String questionDetails;
	private String authorComment;
	private int timer;
	private int likes = 0; // デフォルト値を0に設定
	private int playCount = 0; // デフォルト値を0に設定
	private LocalDateTime createdDate;
	private UUID userId; // 外部キーとしてユーザーIDを使用
}