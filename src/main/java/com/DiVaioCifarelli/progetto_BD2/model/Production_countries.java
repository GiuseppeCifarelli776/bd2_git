package com.DiVaioCifarelli.progetto_BD2.model;

public class Production_countries {

	private String iso_3166_1;
	private String name;
	
	public Production_countries(String iso_3166_1, String name) {
		this.iso_3166_1 = iso_3166_1;
		this.name = name;
	}
	
	public Production_countries() {
		
	}
	
	public String getIso_3166_1() {
		return this.iso_3166_1;
	}
	public void setIso_3166_1(String iso_3166_1) {
		this.iso_3166_1 = iso_3166_1;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	};	
	
}
