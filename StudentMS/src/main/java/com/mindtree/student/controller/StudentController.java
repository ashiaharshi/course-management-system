package com.mindtree.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.student.dto.StudentDto;
import com.mindtree.student.exception.StudentException;
import com.mindtree.student.exception.service.StudentServiceException;
import com.mindtree.student.service.StudentService;
import com.mindtree.student.service.impl.StudentServiceImpl;
import com.mindtree.student.vo.ResponseTemplateVO;

@RestController
@RequestMapping("/Student")
public class StudentController {

	@Autowired
	private StudentService studentService = new StudentServiceImpl();

	@PostMapping("/addStudent")
	public ResponseEntity<?> addStudent(@RequestBody StudentDto sDto) throws StudentException {
		StudentDto studentDto = null;
		try {
			studentDto = studentService.addStudent(sDto);

		} catch (StudentServiceException e) {
			return new ResponseEntity(e.getClass(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<StudentDto>(studentDto, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getAllStudentByMarks")
	public List<StudentDto> getAllStudentByMarks() {
		return studentService.getAllStudentByMarks();
	}

	@GetMapping("/getAllStudentByMarks/{marks}")
	public ResponseEntity<StudentDto> getAllStudentByMarks(@PathVariable("marks") int marks) throws StudentException {
		List<StudentDto> student = null;
		try {
			student = studentService.getAllStudentByMarks(marks);
			return new ResponseEntity(student, HttpStatus.ACCEPTED);
		} catch (StudentServiceException e) {
			throw new StudentException(e.getMessage());
		}
	}

	@GetMapping("/getAllStudentWithCollege/{id}")
	public ResponseTemplateVO getAllStudentWithCollege(@PathVariable("id") int studId) {
			return studentService.getAllStudentWithCollege(studId);
	}
	
	@PutMapping("/updateStudentDetails/{id}")
	public StudentDto updateStudentDetails(@PathVariable("id") int studId, @RequestBody StudentDto sDto) {
		return studentService.updateStudentDetails(studId, sDto);
	}
}
