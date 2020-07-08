package com.DiVaioCifarelli.progetto_BD2.model;

public class ResultStatics {
	
	//x
	private String genre;
	//y
	private String axis;
		
	public ResultStatics() {
		
	}
	
	public ResultStatics(String genre, String axis) {
		this.setGenre(genre);
		this.setAxis(axis);
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAxis() {
		return axis;
	}

	public void setAxis(String axis) {
		this.axis = axis;
	}


}
