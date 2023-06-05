package com.mindtree.college.dto;

public class CollegeDto {
	
	private String collegeName;
	private int totalStrength,id;
	public CollegeDto() {
		
	}

	public CollegeDto(String collegeName, int totalStrength, int id) {
		this.collegeName = collegeName;
		this.totalStrength = totalStrength;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public int getTotalStrength() {
		return totalStrength;
	}

	public void setTotalStrength(int totalStrength) {
		this.totalStrength = totalStrength;
	}
	
}
