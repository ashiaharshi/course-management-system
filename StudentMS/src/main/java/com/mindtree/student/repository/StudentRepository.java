package com.mindtree.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindtree.student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	@Query("Select s FROM Student s where s.marks > ?1")
	List<Student> findByMarksGreaterThan(int marks);

}
