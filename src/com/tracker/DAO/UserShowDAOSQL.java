package com.tracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tracker.JDBC.ConnectionManager;
import com.tracker.model.UserShow;

public class UserShowDAOSQL implements UserShowDAO {
	private Connection conn = ConnectionManager.getConnection();
	
	@Override
	public List<UserShow> getShowByUserId(int userId) {
		List<UserShow> userShow = new ArrayList<UserShow>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("select user_id, show_id, ts.name, current_episode, ts.episode_count from user_show us join tv_show ts on ts.show_id = us.show_id where user_id = ?");
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				int user_id = rs.getInt("user_id");
				int show_id = rs.getInt("show_id");
				String name = rs.getString("name");
				int currentEpisode = rs.getInt("current_episode");
				int episodeCount = rs.getInt("episode_count");

				userShow.add(new UserShow(user_id, show_id, name, currentEpisode, episodeCount));
			}

			rs.close();
			//return userShow;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userShow;
	}
	
	@Override
	public int getCurrentEpisode(int showId, int userId) {
		int currentEpisode = -1;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("select current_episode from user_show where user_id = ? and show_id = ?");
			pstmt.setInt(1, userId);
			pstmt.setInt(2, showId);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				currentEpisode = rs.getInt("current_episode");
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return currentEpisode;
	}

	@Override
	public int getRating(int showId, int userId) {
		int rating = -1;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("select rating from user_show where user_id = ? and show_id = ?");
			pstmt.setInt(1, userId);
			pstmt.setInt(2, showId);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				rating = rs.getInt("rating");
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rating;
	}


	@Override
	public boolean updateCurrentEpisode(int userInput, int userId, int showId) {
		try(PreparedStatement pstmt = conn.prepareStatement("update user_show set current_episode = ? where user_id = ? and show_id = ?");) {
			pstmt.setInt(1, userInput);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, showId);
			
			int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateRating(int showId, int userId, int userInput) {
		try(PreparedStatement pstmt = conn.prepareStatement("update user_show set rating = ? where user_id = ? and show_id = ?");) {
			pstmt.setInt(1, userInput);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, showId);
			
			int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
