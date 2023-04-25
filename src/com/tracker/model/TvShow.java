package com.tracker.model;

public class TvShow {

	private int show_id;
	private String name;
	private String description;
	private int episodeCount;

	
	public TvShow(int show_id, String name, String description, int episodeCount) {
		super();
		this.show_id = show_id;
		this.name = name;
		this.description = description;
		this.episodeCount = episodeCount;
	}


	public int getShow_id() {
		return show_id;
	}


	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getEpisodeCount() {
		return episodeCount;
	}


	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}


	@Override
	public String toString() {
		return "TvShow [show_id=" + show_id + ", name=" + name + ", description=" + description + ", episodeCount="
				+ episodeCount + "]";
	}
	
	
	
	
	
	
}
