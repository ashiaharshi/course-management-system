package com.mindtree.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.course.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

	@Query("Select c from Course c where c.collegeId=?1")
	List<Course> findCourseById(int id);

}
