package com.tracker.controller;

import java.util.Scanner;

import com.tracker.DAO.UserDAOSQL;
import com.tracker.model.User;
import com.tracker.utility.ConsoleColors;

public class MenuController {
	
	static Scanner input = new Scanner(System.in);
	
	static User activeAccount; // need user model
	
	static UserDAOSQL uds = new UserDAOSQL();
	
	public static void startMenu() {
        String choice;

        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|               PROGRESS TRACKER              |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                                             |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                1. REGISTER                  |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                2. LOGIN                     |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                3. EXIT                      |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                                             |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Please make a selection");

        choice = input.nextLine();

        switch (choice) {
            case "1" -> registerMenu();
            case "2" -> login();
            case "3" -> {
                System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nCome back soon!");
                input.close();
                System.exit(0);
            }
            default -> {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter a valid input");
                startMenu();
            }
        }
    }
	
	public static void registerMenu() {
		String username;
		String password;
		
		System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Welcome to the account creation page!");
		
		
		try {
			System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Please enter your desired user name");

			username = input.nextLine();
			
			System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Please enter your desired password");
			
			password = input.nextLine();
			
			User user = new User(0, username, password, "user");
			
			if (uds.createUser(user)) {// need a create user method 
				System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Account successfully created, please log in");
				
				startMenu();
			}
		} catch (Exception e) {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Username was taken please try again");
			registerMenu();
		}
	}

	public static void login() {
        String username;
        String password;

        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Welcome to the Login Page!");
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Please enter your username:\n");

        username = input.nextLine();

        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Please enter your password:\n");

        password = input.nextLine();
        
        User user = new User();
        
        user.setName(username);
        user.setPassword(password);

        if (uds.login(user) != null) { // need a method to check if the users credentials match a row in the database
            activeAccount = uds.login(user); // need a method to get a user by their username
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Log in successful!\n");
            if (activeAccount.getRole().equals("Admin")) { // need a role getter on the user class
            	adminMenu();
            } else {
            	userMenu();
            }
        } else {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Log in unsuccessful, please try again\n");
            login();
        }

	}
	
	public static void userMenu() {
		String choice;
		
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|               PROGRESS TRACKER              |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                                             |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|          1. VIEW IN PROGRESS SHOWS          |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|          2. TRACK NEW SHOW                  |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|          3. CHECK GLOBAL PROGRESS           |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|          4. BACK                            |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                                             |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Please make a selection");

        choice = input.nextLine();

        switch (choice) {
            case "1" -> ShowController.viewShows(input);
            case "2" -> ShowController.addShow(input);
            case "3" -> ShowController.viewGlobalShows(input);
            case "4" -> startMenu();
            default -> {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter a valid input");
                userMenu();
            }
        }
	}
	
	public static void adminMenu() {
		String choice;
		
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|               PROGRESS TRACKER              |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                                             |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|          1. VIEW IN PROGRESS SHOWS          |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|          2. TRACK NEW SHOW                  |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|          3. CHECK GLOBAL PROGRESS           |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|          4. EDIT SHOW INFORMATION           |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|          5. BACK                            |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                                             |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Please make a selection");

        choice = input.nextLine();

        switch (choice) {
            case "1" -> ShowController.viewShows(input);
            case "2" -> ShowController.addShow(input);
            case "3" -> ShowController.viewGlobalShows(input);
            case "4" -> ShowController.editShow(input);
            case "5" -> startMenu();
            default -> {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter a valid input");
                adminMenu();
            }
        }
	}

}
