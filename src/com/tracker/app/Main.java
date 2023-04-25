package com.tracker.app;

import java.util.List;

import com.tracker.DAO.TvShowDAO;
import com.tracker.DAO.UserDAO;
import com.tracker.DAO.UserDAOSQL;
import com.tracker.model.User;
import com.tracker.services.Message;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		
		TvShowDAO tv = new TvShowDAO();
		
		System.out.println(tv.getAllShows());
		

		
		
		
		
		//user Creation
		UserDAO userDAO = new UserDAOSQL();
		//User user = new User(1, "Peppa" , "peppa@123" , "user");
		
		//userDAO.createUser(user);
		
		//Get user
		//		user.setId(6);
	
		//		Message.message(userDAO.getUser(user).toString());
		
		
		//		userDAO.removeUser(user);
		
		//		userDAO.updateUser(3, "role", "admin" );
		
		List<User> userList = userDAO.getAllUsers() ;
		
		for (User usr : userList){
			System.out.println(usr.toString());
		}
		
		User user = new User(3, "eric" , "eric@123" , "admin");
		user.setId(3);
		User loggedInUser = userDAO.login(user);
		Message.message(loggedInUser.toString());

	}

}
