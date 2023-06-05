package com.mindtree.course.service;

import java.util.List;

import com.mindtree.course.entity.Course;

public interface CourseService {

	public Course addCourse(Course cDto);

	public List<Course> fetchAllCoursesByCollege(String collegeName) throws InterruptedException;

	public Course updateCourse(Course cDto, int courseId);

	public void deleteCourse(int courseId);

}
