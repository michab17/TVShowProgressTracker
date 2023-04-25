package com.tracker.app;

import com.tracker.dao.TvShowDAO;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		
		TvShowDAO tv = new TvShowDAO();
		
		System.out.println(tv.getAllShows());
		

	}

}
