package com.DiVaioCifarelli.progetto_BD2.model;

public class Cast {
	private Integer cast_id;
	private String character;
	private String credit_id;
	private Integer gender;
	private Integer id;
	private String name;
	private Integer order;
	
	public Cast(Integer c_id, String chr, String cr_id, Integer gend, Integer id, String name, Integer ord) {
		this.cast_id = c_id;
		this.character = chr;
		this.gender = gend;
		this.id = id;
		this.name = name;
		this.order = ord;
	}
	
	public Integer getCast_id() {
		return cast_id;
	}
	public void setCast_id(Integer cast_id) {
		this.cast_id = cast_id;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCredit_id() {
		return credit_id;
	}
	public void setCredit_id(String credit_id) {
		this.credit_id = credit_id;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}
