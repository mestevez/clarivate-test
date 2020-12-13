package com.clarivate.testrestapi.score;


import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {

	private @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long scoreId;

	private long userId;

	private int level;

	private int value;

	public Score() {
	}

	/**
	 * Custom constructor when need to create a new instance, but don't have and scoreId yet.
	 * @param userId
	 * @param level
	 * @param value
	 */
	public Score(long userId, int level, int value) {
		this.userId = userId;
		this.level = level;
		this.value = value;
	}

	public int getLevel() {
		return level;
	}

	public Long getScoreId() {
		return scoreId;
	}

	public long getUserId() {
		return userId;
	}

	public int getValue() {
		return value;
	}

	public void setLevel(int levelId) {
		this.level = levelId;
	}

	public void setScoreId(Long scoreid) {
		this.scoreId = scoreid;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format(
				"Score {id=%s, userId=%s, level=%s, value=%s}",
				scoreId,
				userId,
				level,
				value
		);
	}
}
