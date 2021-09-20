package ch.swissre.components;

/*Customer loyalty card for stamps colleting.Every 5th drink is free*/
public class StampCard {

	private static final int STAMP_LIMIT = 5;
	private Integer stampCardId;
	private Integer stamps = 0;
	private Integer rewards = 0;

	public StampCard(int stampCardId) {

		this.stampCardId = stampCardId;

	}

	public int getNumberOfStamps() {
		return stamps;
	}

	public void addStamp(int numberOfStamps) {

		int mod = (stamps + numberOfStamps) % STAMP_LIMIT;

		if ((mod == 0) && (stamps + numberOfStamps) >= STAMP_LIMIT) {
			rewards = ((stamps + numberOfStamps) / STAMP_LIMIT);
			stamps = 0;
		} else if ((mod >= 0) && (stamps + numberOfStamps) < STAMP_LIMIT) {
			rewards = ((stamps + numberOfStamps) / STAMP_LIMIT);
			stamps += numberOfStamps;

		} else if ((mod > 0) && (stamps + numberOfStamps) > STAMP_LIMIT) {
			rewards = ((stamps + numberOfStamps) / STAMP_LIMIT);
			stamps = mod;
		}

	}

	public void setStamps(Integer stamps) {
		this.stamps = stamps;
	}

	public Integer getRewards() {
		return rewards;
	}

	public Integer getStampCardId() {
		return stampCardId;
	}

	public void setStampCardId(Integer stampCardId) {
		this.stampCardId = stampCardId;
	}

	public void setRewards(Integer rewards) {
		this.rewards = rewards;
	}
}
