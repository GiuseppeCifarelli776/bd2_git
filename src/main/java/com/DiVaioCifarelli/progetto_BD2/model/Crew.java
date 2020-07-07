package com.DiVaioCifarelli.progetto_BD2.model;

public class Crew {

	private String credit_id;
	private String departement;
	private Integer gender;
	private Integer id;
	private String job;
	private String name;
	
	public Crew(String cred_id, String dep, Integer gend, Integer id, String job, String name) {
		this.setCredit_id(cred_id);
		this.setDepartement(dep);
		this.setGender(gend);
		this.setId(id);
		this.setJob(job);
		this.setName(name);
	}

	
	public Crew() {
		
	}
	
	public String getCredit_id() {
		return credit_id;
	}

	public void setCredit_id(String credit_id) {
		this.credit_id = credit_id;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
