package com.DiVaioCifarelli.progetto_BD2.model;

public class Production_companies {
	
	private Integer id;
	private String name;
	
	public Production_companies(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
