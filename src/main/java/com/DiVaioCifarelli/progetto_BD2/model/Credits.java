package com.DiVaioCifarelli.progetto_BD2.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Credits")
public class Credits {
	
//	Elenco attributi del documento Film
	
	@Id
	private String _id;
	
	private String movie_id;
	private String title;
	private List<Cast> cast;
	private List<Crew> crew;
	
	
	
	public Credits(String _id, String m_id, String title, List<Cast> c, List<Crew> cr) {
		this._id = _id;
		this.setMovie_id(m_id);
		this.setTitle(title);
		this.setCast(c);
		this.setCrew(cr);
	}
	
	public Credits() {
		
	}

	public String getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Cast> getCast() {
		return cast;
	}

	public void setCast(List<Cast> cast) {
		this.cast = cast;
	}

	public List<Crew> getCrew() {
		return crew;
	}

	public void setCrew(List<Crew> crew) {
		this.crew = crew;
	}
}
