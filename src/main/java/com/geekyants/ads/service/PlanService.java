package com.geekyants.ads.service;

import java.text.ParseException;

import com.geekyants.ads.dto.PlanRequestDto;
import com.geekyants.ads.dto.PlanResponseDto;
import com.geekyants.ads.dto.ResponseDto;
import com.geekyants.ads.exception.AlreadyTakenException;
import com.geekyants.ads.exception.NotFoundException;

public interface PlanService {
	
	ResponseDto addPlan(PlanRequestDto planRequestDto) throws ParseException, AlreadyTakenException;

	PlanResponseDto getAllPlans() throws NotFoundException;

}
