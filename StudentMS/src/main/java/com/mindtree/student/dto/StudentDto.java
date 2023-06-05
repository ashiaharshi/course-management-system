package com.mindtree.student.dto;

public class StudentDto {
	private int id;
	private String studentName;
	private int marks;
	private String collegeName;
	private int courseId;
	
	public StudentDto() {
		
	}
	public StudentDto(int id, String studentName, int marks, String collegeName, int courseId) {
		this.id = id;
		this.studentName = studentName;
		this.marks = marks;
		this.collegeName = collegeName;
		this.courseId = courseId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
}
