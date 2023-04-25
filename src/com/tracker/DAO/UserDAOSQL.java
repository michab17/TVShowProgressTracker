package com.tracker.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tracker.model.User;
import com.tracker.services.Message;

public class UserDAOSQL implements UserDAO {
	
	private Connection conn = com.tracker.JDBC.ConnectionManager.getConnection();

	@Override
	public boolean createUser(User user) {
		PreparedStatement pstmt = null;
		String queryStr = "INSERT INTO User(user_id, username, password, role) values( ?, ?, ?, ?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(queryStr);
			pstmt.setInt(1, 0);
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4,user.getRole());
			result = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result > 0;
	}

	@Override
	public User getUser(User searchUser) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String queryStr = "SELECT * FROM User where User.user_id = ?";
		
		User foundUser = null;
		
		try {
			pstmt = conn.prepareStatement(queryStr);
			pstmt.setInt(1, searchUser.getId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				foundUser = new User(
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("role")
				);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return foundUser;

	}

	@Override
	public boolean removeUser(User removedUser) {
		PreparedStatement pstmt = null;
		String queryStr = "DELETE FROM User WHERE User.user_id= ?";
		int result = 0;
		try {
			
			pstmt = conn.prepareStatement(queryStr);
			pstmt.setInt(1, removedUser.getId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result > 0;
		
	}
	
	/**
	 * 
	 * @param studentId
	 * @param field : password or role
	 * @param value : password value or role value
	 * @return
	 */
	@Override
	public boolean updateUser(int studentId, String field, String value) { 
		PreparedStatement pstmt = null;
		String queryStr = "UPDATE User SET "+ field+ " = ? WHERE User.user_id = ?";
		int result = 0;
		try{
			pstmt = conn.prepareStatement(queryStr);
			pstmt.setString(1, value);
			pstmt.setInt(2, studentId);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result > 0;
		
	}

	@Override
	public List<User> getAllUsers() {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();
		String queryStr = "SELECT * FROM User";
		
		try {
			pstm = conn.prepareStatement(queryStr);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				list.add(new User(
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("role")
				));
				
			}
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public User login(User user) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "Select user_id, username , role, password from User where User.username = ? ";
		
		int storedId = -1;
		String storedName = null;
		String storedPassword = null;
		String storedRole = "";
		//query customer with email and check password
		try {
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, user.getName());
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 storedId = rs.getInt("user_id");
				 storedName = rs.getString("username");
				 storedPassword = rs.getString("password");
				 storedRole = rs.getString("role");
			 }
			 boolean isExist =  user.getName().equals(storedName) && 
						user.getPassword().equals(storedPassword);
			if(isExist) {
				user.setId(storedId);
				user.setRole(storedRole);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
}
