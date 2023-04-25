package com.tracker.DAO;

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
			rs.close();
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
			rs.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}
		return null;

	}

//////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public String getShowName(int showId) {

		String name = "NULL";
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from tv_show where show_id = ?");
			pstmt.setInt(1, showId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				name = rs.getString("name");

			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}
		return name;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public String getShowDescription(int showId) {

		String description = "NULL";
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from tv_show where show_id = ?");
			pstmt.setInt(1, showId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				description = rs.getString("description");

			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}
		return description;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public int getShowEpisodeCount(int showId) {

		int epiCount = -1;

		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from tv_show where show_id = ?");
			pstmt.setInt(1, showId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				epiCount = rs.getInt("episode_count");

			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}

		return epiCount;
	}

//////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public boolean updateName(int showId, String userInput) {
		try (PreparedStatement pstmt = conn.prepareStatement("update tv_show set name = ? where show_id = ?;");) {

			pstmt.setString(1, userInput);
			pstmt.setInt(2, showId);

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}

		return false;
	}

//	int rowsAffected = pstmt.executeUpdate();
//
//    if (rowsAffected > 0) {
//        return true;
//    }"
//////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean updateDescription(int showId, String userInput) {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update tv_show set description = ? where show_id = ?;");) {

			pstmt.setString(1, userInput);
			pstmt.setInt(2, showId);

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}

		return false;
// update tv_show set description = "Drugs N' Money" where show_id = 1;

	}

//////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public boolean updateEpisodeCount(int showId, int userInput) {

		try (PreparedStatement pstmt = conn
				.prepareStatement("update tv_show set episode_count = ? where show_id = ?;");) {

			pstmt.setInt(1, userInput);
			pstmt.setInt(2, showId);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
		}

		return false;
	}

}
