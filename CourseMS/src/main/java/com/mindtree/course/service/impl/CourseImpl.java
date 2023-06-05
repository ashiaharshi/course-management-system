package com.mindtree.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mindtree.course.entity.Course;
import com.mindtree.course.repository.CourseRepository;
import com.mindtree.course.service.CourseService;
import com.mindtree.student.vo.College;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CourseImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Course addCourse(Course cDto) {
		return courseRepository.save(cDto);
	}

	@Override
	@Cacheable(value = "courseCache")
	public List<Course> fetchAllCoursesByCollege(String collegeName) throws InterruptedException {
		Thread.sleep(2000);
		College college = restTemplate.getForObject("http://localhost:8901/College/fetchDetailsByName/" + collegeName,
				College.class);
		List<Course> course = courseRepository.findCourseById(college.getId());
		return course;
	}

	@Override
	@CachePut(value = "courseCache")
	public Course updateCourse(Course cDto, int courseId) {
		Course course = courseRepository.findById(courseId).get();
		course.setCollegeId(cDto.getCollegeId());
		course.setCourseName(cDto.getCourseName());
		course.setCutOffMarks(courseId);
		return courseRepository.save(course);
	}

	@Override
	public void deleteCourse(int courseId) {
		courseRepository.deleteById(courseId);
	}

}
