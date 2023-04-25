package com.tracker.DAO;

import java.util.List;

import com.tracker.model.User;

public interface UserDAO {
	
	public User login(User user);
	
	public boolean createUser(User user);
	
	public User getUser(int userId);
	
	public boolean removeUser(User user);
	
	public boolean updateUser(int studentId, String field, String value);
	
	public List<User> getAllUsers();
	
}
