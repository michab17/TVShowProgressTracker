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
        
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter the corresponding number for a show to see more information or type b to go back");
        
        choice = input.nextLine();
        
        try {
        	if (choice.equalsIgnoreCase("b")) {
        		MenuController.adminCheck(MenuController.activeAccount.getRole());
        	} else if (showList.get(Integer.parseInt(choice) - 1) != null) {
	            TvShow show = tvsd.getShowbyId(showList.get(Integer.parseInt(choice) - 1).getShowId());
	
	            viewShow(input, show);
	        } else {
	            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Please enter a valid input!");
	        }
        } catch (Exception e) {
        	Message.error("Please enter a number corresponding to a show!");
        }
	}
	
	public static void viewShow(Scanner input, TvShow show) {
		String choice;
		int userId = MenuController.activeAccount.getId();
		
		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Show Name: " + show.getName());
		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Show Description : " + show.getDescription());
		
		double currentEpisode = usds.getCurrentEpisode(show.getShow_id(), userId);
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
		System.out.println("]");
		
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
					
					if (usds.updateCurrentEpisode(newEpisodeNumber, userId, show.getShow_id())) {
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
				if (usds.deleteUserShow(show.getShow_id(), userId)) {
					Message.message("Show successfully deleted!");
					viewShows(input);
				} else {
					Message.error("The Show could not be deleted");
					viewShow(input, show);
				}
			}
			case "3" -> {
				int rating = usds.getRating(show.getShow_id(), userId);
				if (rating != -1) {
					Message.message("Your current rating for " + show.getName() + " is " + rating);
				}
				Message.message("Please enter your desired rating");
				
				choice = input.nextLine();
				
				try {
					int newRating = Integer.parseInt(choice);
					
					if (usds.updateRating(show.getShow_id(), userId, newRating) && newRating <= 5 && newRating >= 1) {
						Message.message("Rating updated successfully!!");
						Message.message("Your new rating is now " + newRating);
						viewShow(input, show);
					} else {
						Message.error("Please enter a number between 1 and 5");
						viewShow(input, show);
					}
				} catch (Exception e) {
					Message.error("Please enter a number between 1 and 5");
					viewShow(input, show);
				}
			}
			case "4" -> viewShows(input);
			default -> {
				Message.error("Please enter a valid input");
				viewShow(input, show);
			}
		}
	}

	public static void addShow(Scanner input) {
		String choice;
        int counter = 1;
        int userId = MenuController.activeAccount.getId();
        
        List<TvShow> showList = usds.getUntrackedShows(userId); // user model getUserId getter

        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|               PROGRESS TRACKER              |");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                                             |");
        System.out.printf("| %-28s %-9s |\n", "SHOW NAME", "EPISODE COUNT");
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "|                                             |");
		for (TvShow show : showList) {
            System.out.printf("|  %-2s %-30s %-8s |\n", counter++, show.getName(), show.getEpisodeCount());
        }
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "+=============================================+");
        
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter the corresponding number for a show to see more information");
        
        choice = input.nextLine();
        
        try {
        	int showId = showList.get(Integer.parseInt(choice) - 1).getShow_id();
        	
        	if(usds.addUserShow(showId, userId)) {
        		Message.message("Show successfully added!");
        		MenuController.adminCheck(MenuController.activeAccount.getRole());
        	} else {
        		Message.error("Please enter a number corresponding to a show!");
        	}
        		
        } catch (Exception e) {
        	Message.error("Please enter a number corresponding to a show!");
        }
	}
	
	public static void viewGlobalShows(Scanner input) {
		
	}
	
	public static void editShow(Scanner input) {
			
			Message.message("Enter the show Id.");
			int showId = Integer.parseInt(input.nextLine());
			Message.title("Enter 1: to edit description /n 2: to edit the numbers of show.");
			String choice = input.nextLine();
			switch(choice) {
				case "1":
					Message.message("Enter the descriptions of show: ");
					String newDescription = input.nextLine();
					tvsd.updateDescription(showId, newDescription);
					break;
				case "2":
					Message.title("Enter the new numbers of show.");
					int numbersOfShow = input.nextInt();
					tvsd.updateEpisodeCount(showId, numbersOfShow);
					break;
				default:{
					Message.error("Invalid Input");
					editShow(input);
					}
				
			}
			Message.message("Show has been updated successful");
			
	}
}
