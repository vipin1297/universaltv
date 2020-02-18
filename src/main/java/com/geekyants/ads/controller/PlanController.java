package com.geekyants.ads.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geekyants.ads.dto.PlanRequestDto;
import com.geekyants.ads.dto.PlanResponseDto;
import com.geekyants.ads.dto.ResponseDto;
import com.geekyants.ads.exception.NotFoundException;
import com.geekyants.ads.service.PlanService;

@RestController
@CrossOrigin(allowedHeaders = {"*","*/"}, origins = {"*","*/"})
public class PlanController {
	@Autowired
	PlanService planService;
	
	/**
	 * @author Vinod B N
	 * 
	 * Method is used to add the plan option and allows different planType option
	 * 
	 * @param planRequestDto
	 * @return ResponseDto Displays the  status
	 *         code and message
	 */
	@PostMapping("/plans") 
	public ResponseDto addPlan(@RequestBody PlanRequestDto planRequestDto) throws ParseException {
		return planService.addPlan(planRequestDto); 
	}
	/**
	 * @author Raghib
	 * 
	 *         Method is used to get all the plan option 
	 * 
	 * @return PlanResponseDto Displays the  planDetails
	 *         code and message
	 */
	@GetMapping("/plans")
	public ResponseEntity<PlanResponseDto> getPlans() throws NotFoundException {

		return ResponseEntity.ok().body(planService.getAllPlans());
	}

}
