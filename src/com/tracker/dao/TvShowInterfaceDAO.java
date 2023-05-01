package com.tracker.DAO;

import java.util.List;

import com.tracker.model.TvShow;

public interface TvShowInterfaceDAO {
	
	public List<TvShow> getAllShows();
	public TvShow getShowbyId(int showId);
	public String getShowName(int showId);
	public String getShowDescription(int showId);
	public int getShowEpisodeCount(int showId);
	public boolean updateName(int showId, String userInput);
	public boolean updateDescription(int showId, String userInput);
	public boolean updateEpisodeCount(int showId, int userInput);

}
