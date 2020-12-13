package com.clarivate.testrestapi.score;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Score {

	private @Id
	@GeneratedValue
	Long scoreId;

	private int userId;

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
	public Score(int userId, int level, int value) {
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

	public int getUserId() {
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

	public void setUserId(int userId) {
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
