package com.DiVaioCifarelli.progetto_BD2.model;

public class Spoken_languages {

	private String iso_639_1;
	private String name;
	
	public Spoken_languages(String iso_639_1, String name) {
		this.iso_639_1 = iso_639_1;
		this.name = name;
	}
	
	public String getIso_639_1() {
		return this.iso_639_1;
	}
	public void setIso_3166_1(String iso_639_1) {
		this.iso_639_1 = iso_639_1;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	};	
}
