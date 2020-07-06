package com.DiVaioCifarelli.progetto_BD2.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Film")
public class Film {

//	Elenco attributi del documento Film
	
	@Id
	private String _id;
	
	private String budget;
	private List<Genere> genres;
	private String homepage;
	private String id;
	private List<Keyword> keywords;
	private String original_language;
	private String original_title;
	private String overview;
	private String popularity;
	private List<Production_companies> production_companies;
	private List<Production_countries> production_countries;
	private String release_date;
	private String revenue;
	private String runtime;
	private List<Spoken_languages> spoken_languages;
	private String status;
	private String tagline;
	private String title;
	private String vote_average;
	private String vote_count;
	
	public Film(String _id, String budget, List<Genere> genres, String homepage, String original_lang, String original_title, 
			String overview, String popularity, List<Production_companies> prod_companies, List<Production_countries> prod_countries,
			String rel_date, String revenue, String runtime, List<Spoken_languages> spok_lang, String status, String tagline, String title, 
			String vote_avg, String vote_cnt) {
		
		this._id = _id;
		this.budget = budget;
		this.genres = genres;
		this.homepage = homepage;
		this.original_language = original_lang;
		this.original_title = original_title;
		this.overview = overview;
		this.popularity = popularity;
		this.production_companies = prod_companies;
		this.production_countries = prod_countries;
		this.release_date = rel_date;
		this.revenue = revenue;
		this.runtime = runtime;
		this.spoken_languages = spok_lang;
		this.status = status;
		this.tagline = tagline;
		this.title = title;
		this.vote_average = vote_avg;
		this.vote_count = vote_cnt;
		
	}
	
	
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public List<Genere> getGenres() {
		return genres;
	}
	public void setGenres(List<Genere> genres) {
		this.genres = genres;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Keyword> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}
	public String getOriginal_language() {
		return original_language;
	}
	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}
	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getPopularity() {
		return popularity;
	}
	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	public List<Production_companies> getProduction_companies() {
		return production_companies;
	}
	public void setProduction_companies(List<Production_companies> production_companies) {
		this.production_companies = production_companies;
	}
	public List<Production_countries> getProduction_countries() {
		return production_countries;
	}
	public void setProduction_countries(List<Production_countries> production_countries) {
		this.production_countries = production_countries;
	}
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public List<Spoken_languages> getSpoken_languages() {
		return spoken_languages;
	}
	public void setSpoken_languages(List<Spoken_languages> spoken_languages) {
		this.spoken_languages = spoken_languages;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVote_average() {
		return vote_average;
	}
	public void setVote_average(String vote_average) {
		this.vote_average = vote_average;
	}
	public String getVote_count() {
		return vote_count;
	}
	public void setVote_count(String vote_count) {
		this.vote_count = vote_count;
	}
}
