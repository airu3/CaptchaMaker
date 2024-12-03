package team.g.cockroach.controller.response;

@Data
public class StageCardResponse {
	private String id;
	private String stageName;
	private String stageQuestion;
	private String thumbnailUrl;
	private int timer;
	private int likes;
	private int playCount;
	private double clearRate;
	private LocalDateTime createdAt;
	private UserInfo creator;

	@Data
	public static class UserInfo {
		private String id;
		private String name;
		private String avatarUrl;
	}
}
