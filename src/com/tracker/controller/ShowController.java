package com.tracker.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.tracker.DAO.UserShow;
import com.tracker.DAO.UserShowDAOSQL;
import com.tracker.utility.ConsoleColors;

public class ShowController {
	
	static UserShowDAOSQL usds = new UserShowDAOSQL();
	
	public static void viewShows(Scanner input) {
        String choice;
        int counter = 1;
        
        ArrayList<UserShow> showList = usds.getShowByUserId(MenuController.activeAccount.getUserId()); // user model getUserId getter

        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|               PROGRESS TRACKER              |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                                             |");
		for (UserShow show : showList) {
            System.out.printf("|  %-2s %-45s |\n", counter++, show.getShowName());
        }
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter the corresponding number for a show to see more information");
        
        choice = input.nextLine();
        
        try {
	        if (showList.get(Integer.parseInt(choice) - 1) != null) {
	            int studentId = studentDao.getStudentId(studentList.get(Integer.parseInt(choice) - 1).getName());
	
	            StudentController.viewStudent(studentId, input, classId, studentList);
	        } else {
	            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter a valid input!");
	        }
        } catch (Exception e) {
        	
        }
	}

	public static void addShow(Scanner input) {
		
	}
	
	public static void viewGlobalShows(Scanner input) {
		
	}
	
	public static void editShow(Scanner input) {
		
	}
}
