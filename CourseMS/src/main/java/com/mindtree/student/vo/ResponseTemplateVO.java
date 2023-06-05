package com.mindtree.student.vo;

public class ResponseTemplateVO {

	private College college;
	public ResponseTemplateVO() {
		
	}
	
	public ResponseTemplateVO(College college) {
		super();
		this.college = college;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
	
	
}
