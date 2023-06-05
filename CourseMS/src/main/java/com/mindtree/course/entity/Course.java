package com.mindtree.course.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	private int collegeId;
	private String courseName;
	private int cutOffMarks;
	
	public Course() {
		
	}
	
	public Course(int courseId, int collegeId, String courseName, int cutOffMarks) {
		super();
		this.courseId = courseId;
		this.collegeId = collegeId;
		this.courseName = courseName;
		this.cutOffMarks = cutOffMarks;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCutOffMarks() {
		return cutOffMarks;
	}

	public void setCutOffMarks(int cutOffMarks) {
		this.cutOffMarks = cutOffMarks;
	}
	
}
