package com.DiVaioCifarelli.progetto_BD2.model;

import java.util.List;

public class ResultTable {
	
	private String title;
	private List<String> genres;
	private List<String> prod_comp;
	private List<String> prod_countr;
	private String release_date;
	private String overview;
	private String vote_avg;
	
	public ResultTable(String title, List<String> genres, List<String> prod_comp, List<String> prod_countr, 
			String rel_date, String ovrw, String avg) {
		this.setTitle(title);
		this.setGenres(genres);
		this.setProd_comp(prod_comp);
		this.setProd_countr(prod_countr);
		this.setRelease_date(rel_date);
		this.setOverview(ovrw);
		this.setVote_avg(avg);
	}
	
	public ResultTable() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<String> getProd_comp() {
		return prod_comp;
	}

	public void setProd_comp(List<String> prod_comp) {
		this.prod_comp = prod_comp;
	}

	public List<String> getProd_countr() {
		return prod_countr;
	}

	public void setProd_countr(List<String> prod_countr) {
		this.prod_countr = prod_countr;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getVote_avg() {
		return vote_avg;
	}

	public void setVote_avg(String vote_avg) {
		this.vote_avg = vote_avg;
	}
}
