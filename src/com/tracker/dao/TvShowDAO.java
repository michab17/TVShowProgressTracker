package com.tracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tracker.JDBC.ConnectionManager;
import com.tracker.model.TvShow;

public class TvShowDAO implements TvShowInterfaceDAO {

	private static final Connection conn = ConnectionManager.getConnection();
	

	@Override
	public List<TvShow> getAllShows() {
		ArrayList<TvShow> tvShowList = new ArrayList<>();
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("select * from tv_show");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("show_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int epiCount = rs.getInt("episode_count");

				tvShowList.add(new TvShow(id, name, description, epiCount));

			}

		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}

		return tvShowList;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public TvShow getShowbyId(int showId) {

		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from tv_show where show_id = ?");
			pstmt.setInt(1, showId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("show_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int epiCount = rs.getInt("episode_count");

				TvShow byId = new TvShow(id, name, description, epiCount);
				return byId;
			}

		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}
		return null;

	}

//////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public int updateShowById(int showId, int userInput) {

		return 0;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void updateDescription(int showId, String userInput) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("update tv_show set description = ? where show_id = ?;");
			pstmt.setString(1, userInput);
			pstmt.setInt(2, showId);

		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}

		// update tv_show set description = "Drugs N' Money" where show_id = 1;

	}

}
