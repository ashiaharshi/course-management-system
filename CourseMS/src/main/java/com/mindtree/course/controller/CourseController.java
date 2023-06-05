package com.mindtree.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.course.entity.Course;
import com.mindtree.course.service.CourseService;

@RestController
@RequestMapping("/Course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping("/addCourse")
	public ResponseEntity<?> addCourse(@RequestBody Course cDto){
		Course course = courseService.addCourse(cDto);
		return new ResponseEntity<Course>(course, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/fetchAllCoursesByCollege/{name}")
	public List<Course> fetchAllCoursesByCollege(@PathVariable("name") String collegeName) throws InterruptedException{
		List<Course> course = courseService.fetchAllCoursesByCollege(collegeName);
		return course;
	}
	
	@PutMapping("/updateCourse/{Id}")
	public Course updateCourse(@RequestBody Course cDto,@PathVariable("Id") int courseId){
		Course course = courseService.updateCourse(cDto,courseId);
		return course;
	}
	
	@DeleteMapping("/deleteCourse/{Id}")
	public void deleteCourse(@PathVariable("Id") int courseId) {
		courseService.deleteCourse(courseId);
	}
}
