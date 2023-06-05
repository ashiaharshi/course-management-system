package com.mindtree.student.vo;

import com.mindtree.student.entity.Student;

public class ResponseTemplateVO {

	private Student student;
	private College college;
	public ResponseTemplateVO() {
		
	}
	
	public ResponseTemplateVO(Student student, College college) {
		super();
		this.student = student;
		this.college = college;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
	
	
}
