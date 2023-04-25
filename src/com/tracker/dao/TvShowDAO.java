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
	public ArrayList<TvShow> tvShowList = new ArrayList<>();

	@Override
	public List<TvShow> getAllShows() {

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

	@Override
	public List<TvShow> getShowbyId(int showId) {

		return null;
	}

	@Override
	public int updateShowById(int showId, int userInput) {

		return 0;
	}

}
