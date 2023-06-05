package com.mindtree.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mindtree.college.entity.College;

public interface CollegeRepository extends JpaRepository<College, Integer>{

	@Query("Select c from College c where c.collegeName=?1")
	College findByCollegeName(String collegeName);

}
