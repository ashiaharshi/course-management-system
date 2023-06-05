package com.mindtree.college.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class College {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String collegeName;
	private int totalStrength;
	
	public College() {
		
	}

	public College(String collegeName, int totalStrength, int id) {
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
