package com.mindtree.college.service;

import java.util.List;

import com.mindtree.college.dto.CollegeDto;
import com.mindtree.college.entity.College;
import com.mindtree.college.exception.service.CollegeServiceException;

public interface CollegeService {

	CollegeDto addCollege(CollegeDto c) throws CollegeServiceException;

	List<CollegeDto> fetchAllDetails() throws CollegeServiceException, InterruptedException;

	CollegeDto fetchDetailsByName(String collegeName) throws CollegeServiceException;

	CollegeDto updateCollegeById(CollegeDto cDto, int id);

	void deleteCollegeById(int id);

	
}
