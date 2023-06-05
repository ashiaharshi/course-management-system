package com.mindtree.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.college.dto.CollegeDto;
import com.mindtree.college.entity.College;
import com.mindtree.college.exception.CollegeException;
import com.mindtree.college.exception.service.CollegeServiceException;
import com.mindtree.college.service.CollegeService;
import com.mindtree.college.service.impl.CollegeImpl;

@RestController
@RequestMapping("/College")
public class CollegeController {

	@Autowired
	private CollegeService collegeService = new CollegeImpl();

	@PostMapping("/addCollege")
	public ResponseEntity<?> addCollege(@RequestBody CollegeDto c) throws CollegeException {
		CollegeDto collegeDto = null;
		try {
			collegeDto = collegeService.addCollege(c);
			return ResponseEntity.ok(collegeDto);
		} catch (CollegeServiceException e) {
			throw new CollegeException(e);
		}

	}

	@GetMapping("/fetchAllDetails")
	public List<CollegeDto> fetchAllDetails() throws CollegeException, InterruptedException {
		try {
			return collegeService.fetchAllDetails();
		} catch (CollegeServiceException e) {
			throw new CollegeException(e);
		}
	}

	@GetMapping("/fetchDetailsByName/{name}")
	public CollegeDto fetchDetailsByName(@PathVariable("name") String collegeName) throws CollegeException {
		try {
			return collegeService.fetchDetailsByName(collegeName);
		} catch (CollegeServiceException e) {
			throw new CollegeException(e);
		}
	}

	@PutMapping("/updateCollegeById/{id}")
	public CollegeDto updateCollegeById(@RequestBody CollegeDto cDto, @PathVariable int id) {
		return collegeService.updateCollegeById(cDto, id);
	}

	@DeleteMapping("/deleteCollege/{id}")
	public void deleteCollegeById(@PathVariable int id) {
		collegeService.deleteCollegeById(id);
	}

}
