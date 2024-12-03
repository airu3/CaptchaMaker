package team.g.cockroach.domain;

@Entity
@Table(name = "stage_images")
@Data
public class StageImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String stageId;
	private int sequenceNo;
	private String imagePath;
	private boolean isCorrect;
}
