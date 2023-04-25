package com.tracker.model;

public class UserShow {
	private int userId;
	private int showId;
	private String showName;
	private int currentEpisode;
	private int episodeCount;

	public UserShow(int userId, int showId, String showName, int currentEpisode, int episodeCount) {
		super();
		this.userId = userId;
		this.showId = showId;
		this.showName = showName;
		this.currentEpisode = currentEpisode;
		this.episodeCount = episodeCount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getCurrentEpisode() {
		return currentEpisode;
	}

	public void setCurrentEpisode(int currentEpisode) {
		this.currentEpisode = currentEpisode;
	}

	public int getEpisodeCount() {
		return episodeCount;
	}

	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}

	@Override
	public String toString() {
		return "UserShow [userId=" + userId + ", showId=" + showId + ", name=" + showName + ", currentEpisode="
				+ currentEpisode + ", episodeCount=" + episodeCount + "]";
	}
	
}
