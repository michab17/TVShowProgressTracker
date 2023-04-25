package com.tracker.DAO;

import java.util.List;

public interface UserShowDAO {
	
	public List<UserShow> getShowByUserId(int userId);
	
	public int getCurrentEpisode(int showId, int userId);
	
	public int getRating(int showId, int userId);
	
	public boolean updateCurrentEpisode(int showId, int userId, int userInput);
	
	public boolean updateRating(int showId, int userId, int userInput);
	
}
