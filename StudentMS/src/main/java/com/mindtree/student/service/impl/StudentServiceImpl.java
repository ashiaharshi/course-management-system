package com.mindtree.student.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mindtree.student.dto.StudentDto;
import com.mindtree.student.entity.Student;
import com.mindtree.student.exception.service.NoStudentFoundException;
import com.mindtree.student.exception.service.StudentServiceException;
import com.mindtree.student.repository.StudentRepository;
import com.mindtree.student.service.StudentService;
import com.mindtree.student.vo.College;
import com.mindtree.student.vo.ResponseTemplateVO;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RestTemplate restTemplate;

	static ModelMapper mapper = new ModelMapper();

	@Override
	public List<StudentDto> getAllStudentByMarks() {
		List<Student> student = studentRepository.findAll();
		List<StudentDto> studentDto = new ArrayList<StudentDto>();
		StudentDto sDto = new StudentDto();
		Collections.sort(student, new Comparator<Student>() {

			@Override
			public int compare(Student s1, Student s2) {
				return s2.getMarks() - s1.getMarks();
			}
		});
		for (Student s : student) {
			sDto = (mapper.map(s, StudentDto.class));
			studentDto.add(sDto);
		}
		return studentDto;
	}

	@Override
	public StudentDto addStudent(StudentDto sDto) throws StudentServiceException {
		Student student = null;
		Student studentRep = null;
		StudentDto studentDto = null;
		try {
			student = mapper.map(sDto, Student.class);
			studentRep = studentRepository.save(student);
			studentDto = mapper.map(studentRep, StudentDto.class);
		} catch (DataAccessException e) {
			throw new StudentServiceException(e);
		}
		return studentDto;
	}

	@Override
	public List<StudentDto> getAllStudentByMarks(int marks) throws StudentServiceException {
		List<StudentDto> studentDto = new ArrayList<StudentDto>();
		List<Student> student = null;
		StudentDto sDto = new StudentDto();
		try {
			student = studentRepository.findByMarksGreaterThan(marks);
			for (Student s : student) {
				sDto = mapper.map(s, StudentDto.class);
				studentDto.add(sDto);
			}
			if (student.size() == 0)
				throw new NoStudentFoundException("No such student with that marks");
			else
				return studentDto;
		} catch (NoStudentFoundException e) {
			throw new StudentServiceException(e.getMessage());
		}
	}

	@Override
	public ResponseTemplateVO getAllStudentWithCollege(int studId) {
		ResponseTemplateVO template = new ResponseTemplateVO();
		Student student = studentRepository.findById(studId).get();
		College college = restTemplate.getForObject(
				"http://localhost:8901/College/fetchDetailsByName/" + student.getCollegeName(), College.class);
		template.setStudent(student);
		template.setCollege(college);
		return template;
	}

	@Override
	public StudentDto updateStudentDetails(int studId, StudentDto sDto) {
		Student student = studentRepository.findById(studId).get();
		student.setCollegeName(sDto.getCollegeName());
		student.setCourseId(sDto.getCourseId());
		student.setMarks(sDto.getMarks());
		student.setStudentName(sDto.getStudentName());
		sDto = mapper.map(studentRepository.save(student), StudentDto.class);
		return sDto;
	}
}
