package com.mindtree.student.service;

import java.util.List;

import com.mindtree.student.dto.StudentDto;
import com.mindtree.student.exception.service.StudentServiceException;
import com.mindtree.student.vo.ResponseTemplateVO;

public interface StudentService {

	List<StudentDto> getAllStudentByMarks();

	StudentDto addStudent(StudentDto sDto) throws StudentServiceException;

	List<StudentDto> getAllStudentByMarks(int marks) throws StudentServiceException;

	ResponseTemplateVO getAllStudentWithCollege(int studId);

	StudentDto updateStudentDetails(int studId, StudentDto sDto);

}
