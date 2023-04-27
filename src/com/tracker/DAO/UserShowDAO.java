package com.tracker.DAO;

import java.util.HashMap;
import java.util.List;

import com.tracker.model.TvShow;
import com.tracker.model.UserShow;

public interface UserShowDAO {
	
	public List<UserShow> getShowByUserId(int userId);
	
	public int getCurrentEpisode(int showId, int userId);
	
	public int getRating(int showId, int userId);
	
	public boolean updateCurrentEpisode(int showId, int userId, int userInput);
	
	public boolean updateRating(int showId, int userId, int userInput);
	
	public boolean deleteUserShow(int showId, int userId);
	
	public boolean addUserShow(int showId, int userId);
	
	public List<TvShow> getUntrackedShows(int userId);
	
	public HashMap<String, Integer> getShowStatus();
	
	public int getNumTrackingShow(String showName);
}
