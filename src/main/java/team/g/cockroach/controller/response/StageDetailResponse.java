package team.g.cockroach.controller.response;

@Data
public class StageDetailResponse {
	private String id;
	private String stageName;
	private String stageQuestion;
	private String questionDetails;
	private String authorComment;
	private int timer;
	private int likes;
	private int playCount;
	private List<ImageInfo> images;
	private UserInfo creator;

	@Data
	public static class ImageInfo {
		private String imagePath;
		private int sequenceNo;
		private boolean isCorrect;
	}
}