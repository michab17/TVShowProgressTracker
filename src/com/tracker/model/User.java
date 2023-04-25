package com.tracker.model;

import java.util.List;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String role;
	
	private List<TvShow> tvShows = null;

	public User() {
		super();
	}

	public User(int id, String name, String password, String role) {
		super();
		this.id = id;
		this.username = name;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<TvShow> getTvShows() {
		return tvShows;
	}

	public void setTvShows(List<TvShow> tvShows) {
		this.tvShows = tvShows;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
	
			
			
	

}
