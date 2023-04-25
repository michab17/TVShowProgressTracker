package com.tracker.dao;

import java.util.List;

import com.tracker.model.TvShow;

public interface TvShowInterfaceDAO {
	
	public List<TvShow> getAllShows();
	public List<TvShow> getShowbyId(int showId);
	public int updateShowById(int showId, int userInput);
	
	

}
