package com.mindtree.college.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mindtree.college.dto.CollegeDto;
import com.mindtree.college.entity.College;
import com.mindtree.college.exception.service.CollegeNotFoundException;
import com.mindtree.college.exception.service.CollegeServiceException;
import com.mindtree.college.repository.CollegeRepository;
import com.mindtree.college.service.CollegeService;

@Service
public class CollegeImpl implements CollegeService {

	@Autowired
	private CollegeRepository collegeRepository;

	static ModelMapper mapper = new ModelMapper();

	@Override
	@Cacheable(value="cacheCollegeInfo", key="#cDto.id")
	public CollegeDto addCollege(CollegeDto cDto) throws CollegeServiceException {
		College college = null;
		College collegeRep = null;
		CollegeDto collegeDto = null;
		try {
			college = mapper.map(cDto, College.class);
			collegeRep = collegeRepository.save(college);
			collegeDto = mapper.map(collegeRep, CollegeDto.class);
		} catch (DataAccessException e) {
			throw new CollegeServiceException(e);
		}
		return collegeDto;
	}

	@Override
	//@Cacheable(value="cacheCollegeInfo")
	public List<CollegeDto> fetchAllDetails() throws CollegeServiceException, InterruptedException {
		
		List<College> colleges = collegeRepository.findAll();
		List<CollegeDto> collegeDtos = new ArrayList<CollegeDto>();
		CollegeDto collegeDto = new CollegeDto();
		try {
		if(colleges.size()>0) {
		Collections.sort(colleges, new Comparator<College>() {

			@Override
			public int compare(College c1, College c2) {
				int result = c1.getTotalStrength() - c2.getTotalStrength();
				if (result == 0)
					result = c1.getCollegeName().compareTo(c2.getCollegeName());
				return result;
			}

		});
		for(College c: colleges) {
			collegeDto = mapper.map(c, CollegeDto.class);
			collegeDtos.add(collegeDto);
		}
		}
		else
			throw new CollegeNotFoundException("No college found in database");
		}catch (CollegeNotFoundException e) {
			throw new CollegeServiceException(e);
		}
		return collegeDtos;

	}

	@Override
	@Cacheable(value="cacheCollegeInfo", condition = "#collegename=='GSSSIETW'")
	public CollegeDto fetchDetailsByName(String collegeName) throws CollegeServiceException {
		College college = collegeRepository.findByCollegeName(collegeName);
		if(college!=null) {
			return mapper.map(college, CollegeDto.class);
		}
		else
			throw new CollegeServiceException("No such college name Exist");
	}

	@Override
	@CachePut(value="cacheCollegeInfo", key="#id")
	public CollegeDto updateCollegeById(CollegeDto cDto, int id) {
		College college = collegeRepository.findById(id).get();
		college.setCollegeName(cDto.getCollegeName());
		college.setTotalStrength(cDto.getTotalStrength());
		CollegeDto collegeDto = mapper.map(collegeRepository.save(college),CollegeDto.class);
		
		return collegeDto;
	}

	@Override
	@CacheEvict(value = "cacheCollegeInfo", key="#id")
	public void deleteCollegeById(int id) {
		collegeRepository.deleteById(id);
	}

}
