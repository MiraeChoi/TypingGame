package typing4_GameRain;

public class UserInfo_R {

	//사용자의 아이디, 점수, 날짜 불러오기
	private String id;
	private int score;
	private String date;

	public void setId(String id) {
		this.id = id;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public int getScore() {
		return score;
	}

	public String getDate() {
		return date;
	}

}