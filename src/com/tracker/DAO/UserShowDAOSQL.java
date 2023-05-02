package com.tracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tracker.JDBC.ConnectionManager;
import com.tracker.model.TvShow;
import com.tracker.model.UserShow;

public class UserShowDAOSQL implements UserShowDAO {
	private Connection conn = ConnectionManager.getConnection();
	
	@Override
	public List<UserShow> getShowByUserId(int userId) {
		List<UserShow> userShow = new ArrayList<UserShow>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("select user_id, us.show_id, ts.name, current_episode, ts.episode_count from user_show us join tv_show ts on ts.show_id = us.show_id where user_id = ?");
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

	@Override
	public boolean deleteUserShow(int showId, int userId) {
		try(PreparedStatement pstmt = conn.prepareStatement("delete user_show from user_show where user_id = ? and show_id = ?");) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, showId);
			
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
	public boolean addUserShow(int showId, int userId) {
		try(PreparedStatement pstmt = conn.prepareStatement("insert into user_show (user_id, show_id, rating) values (?, ?, null)");) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, showId);
			
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
	public List<TvShow> getUntrackedShows(int userId) {
        List<TvShow> shows = new ArrayList<>();
        
        try {
            PreparedStatement ps = conn.prepareStatement("with tracked_shows as (select ts.name from user_show us join tv_show ts on us.show_id = ts.show_id where user_id = ?) select * from tv_show where tv_show.name not in (select * from tracked_shows)");
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                int show_id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                int episodeCount = rs.getInt(4);

                shows.add(new TvShow(show_id, name, description, episodeCount));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return shows;
	}

	@Override
	public HashMap<String, Integer> getShowStatus() {
		HashMap<String, Integer> status = new HashMap<>();
		
		try {
            PreparedStatement ps = conn.prepareStatement("select ts.name, (ts.episode_count - us.current_episode) as status from user_show us join tv_show ts on us.show_id = ts.show_id");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	String movieName = rs.getString("name");
            	int epCount = rs.getInt("status");
            	
                if (status.containsKey(movieName) && epCount == 0) {
                	status.compute(movieName, (k, v) -> v +=1);
                } else if (epCount == 0) {
                	status.put(movieName, 1);
                } else if (!status.containsKey(movieName) && epCount != 0) {
                	status.put(movieName, 0);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		
		return status;
	}
	
	public Map<String, Integer > getShowsAverage(){
		Map<String, Integer > listShowsAvg = new HashMap<>();
		try {
			String sql = "select name, avg(rating) as show_average  from User_Show us join tv_show ts on us.show_id = ts.show_id group by us.show_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String showName = rs.getString("name");
				Integer showAverage = rs.getInt("show_average");
				
				listShowsAvg.put(showName, showAverage);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listShowsAvg;
	}

	@Override
	public int getNumTrackingShow(String showName) {
		int numOfTrackers = -1;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("select count(us.user_id) from user_show us join tv_show ts on us.show_id = ts.show_id where ts.name = ? group by us.show_id; ");
			pstmt.setString(1, showName);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				numOfTrackers = rs.getInt(1);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return numOfTrackers;
	}
	
	
}
