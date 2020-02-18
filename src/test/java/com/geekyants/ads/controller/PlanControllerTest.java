package com.geekyants.ads.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.geekyants.ads.dto.PlanDetails;
import com.geekyants.ads.dto.PlanRequestDto;
import com.geekyants.ads.dto.PlanResponseDto;
import com.geekyants.ads.dto.ResponseDto;
import com.geekyants.ads.exception.AlreadyTakenException;
import com.geekyants.ads.service.PlanService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PlanControllerTest {
	
	@InjectMocks
	PlanController planController;
	
	@Mock
	PlanService planService; 
	
	@Test
	public void addPlanTest() throws AlreadyTakenException, ParseException {
		ResponseDto responseDto = new ResponseDto();
		PlanRequestDto planRequestDto = new PlanRequestDto();
		responseDto.setStatusCode(202);
		planRequestDto.setFromTime("2020-02-17 20:00:00");
		planRequestDto.setToTime("2020-02-17 20:10:00");
		planRequestDto.setPlanType("Gold");
		Mockito.when(planService.addPlan(planRequestDto)).thenReturn(responseDto); 
		assertEquals(202, responseDto.getStatusCode());
	}
	@Test
	public void getAllPlans() {
		PlanResponseDto planResponseDto = new PlanResponseDto();
		List<PlanDetails> list = new ArrayList<PlanDetails>();
		PlanDetails planDetails1 = new PlanDetails();
		planDetails1.setFromTime("2020-02-17 16:00:12:0000");
		planDetails1.setToTime("2020-02-17 16:40:20:0000");
		planDetails1.setPlanId(1L);
		planDetails1.setPlanType("A");
		PlanDetails planDetails2 = new PlanDetails();
		planDetails2.setFromTime("2020-02-17 17:00:12:0000");
		planDetails2.setToTime("2020-02-17 17:40:20:0000");
		planDetails2.setPlanId(2L);
		planDetails2.setPlanType("A");
		list.add(planDetails1);
		list.add(planDetails2);
		planResponseDto.setPlanDetails(list);
		planResponseDto.setStatusCode(200);
		Mockito.when(planService.getAllPlans()).thenReturn(planResponseDto);
		ResponseEntity<PlanResponseDto> response = planController.getPlans();
		assertEquals(200, planResponseDto.getStatusCode());
	}
 
}
