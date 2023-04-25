package com.tracker.controller;

import java.util.List;
import java.util.Scanner;

import com.tracker.DAO.TvShowDAO;
import com.tracker.DAO.UserShowDAOSQL;
import com.tracker.model.TvShow;
import com.tracker.model.UserShow;
import com.tracker.services.Message;
import com.tracker.utility.ConsoleColors;

public class ShowController {
	
	static UserShowDAOSQL usds = new UserShowDAOSQL();
	static TvShowDAO tvsd = new TvShowDAO();
	
	public static void viewShows(Scanner input) {
        String choice;
        int counter = 1;
        
        List<UserShow> showList = usds.getShowByUserId(MenuController.activeAccount.getId()); // user model getUserId getter

        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|               PROGRESS TRACKER              |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                                             |");
		for (UserShow show : showList) {
            System.out.printf("|  %-2s %-39s |\n", counter++, show.getShowName());
        }
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter the corresponding number for a show to see more information");
        
        choice = input.nextLine();
        
        try {
	        if (showList.get(Integer.parseInt(choice) - 1) != null) {
	            TvShow show = tvsd.getShowbyId(showList.get(Integer.parseInt(choice) - 1).getShowId());
	
	            viewShow(input, show);
	        } else {
	            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter a valid input!");
	        }
        } catch (Exception e) {
        	
        }
	}
	
	public static void viewShow(Scanner input, TvShow show) {
		String choice;
		
		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Show Name: " + show.getName());
		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Show Description : " + show.getDescription());
		
		double currentEpisode = usds.getCurrentEpisode(show.getShow_id(), MenuController.activeAccount.getId());
		double totalEpisode = show.getEpisodeCount();
		
		Message.title("Your current Episode: " + (int) currentEpisode);
		Message.title("Total number of Episodes: " + (int) totalEpisode);
		
		double bars = (currentEpisode / totalEpisode) * 20;
		
		System.out.print("Current Progress: ");
		System.out.print("[");
		for(int i = 0; i < 20; i++) {
			if (i < bars) {
				System.out.print("|");
			} else {
				System.out.print("-");
			}
		}
		System.out.print("]");
		
		Message.message("1. Update current episode");
		Message.message("2. Delete show");
		Message.message("3. Rate show");
		Message.message("4. Back");
		
		choice = input.nextLine();
		
		switch (choice) {
			case "1" -> {
				Message.message("Your current episode is " + currentEpisode);
				Message.message("Please enter the episode you are on");
				
				choice = input.nextLine();
				
				try {
					int newEpisodeNumber = Integer.parseInt(choice);
					
					if (usds.updateCurrentEpisode(newEpisodeNumber, MenuController.activeAccount.getId(), show.getShow_id())) {
						Message.message("Episode updated successfully!!");
						viewShow(input, show);
					} else {
						Message.error("Please enter the number of the episode you are on");
						viewShow(input, show);
					}
				} catch (Exception e) {
					Message.error("Please enter the number of the episode you are on");
					viewShow(input, show);
				}
			}
			case "2" -> {
				
			}
			case "3" -> {
				
			}
			case "4" -> viewShows(input);
			default -> {
				Message.error("Please enter a valid input");
				viewShow(input, show);
			}
		}
	}

	public static void addShow(Scanner input) {
		
	}
	
	public static void viewGlobalShows(Scanner input) {
		
	}
	
	public static void editShow(Scanner input) {
		
	}
}