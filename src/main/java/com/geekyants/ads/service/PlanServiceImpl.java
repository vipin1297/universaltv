package com.geekyants.ads.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.geekyants.ads.constant.ApplicationConstant;
import com.geekyants.ads.dto.PlanDetails;
import com.geekyants.ads.dto.PlanRequestDto;
import com.geekyants.ads.dto.PlanResponseDto;
import com.geekyants.ads.dto.ResponseDto;
import com.geekyants.ads.entity.Plan;
import com.geekyants.ads.entity.Slot;
import com.geekyants.ads.exception.AlreadyTakenException;
import com.geekyants.ads.exception.NoEntriesException;
import com.geekyants.ads.exception.PlanNotFoundException;
import com.geekyants.ads.exception.TimeNotAllowed;
import com.geekyants.ads.repository.PlanRepository;
import com.geekyants.ads.repository.SlotRepository;
/*
 * Method is used to add the plan and get all plans
 */
@Service
public class PlanServiceImpl implements PlanService {
	private static final Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);
	@Autowired
	PlanRepository planRepository; 
	
	@Autowired
	private SlotRepository slotRepository;
	/**
	 * @author Vinod B N
	 * 
	 * Method is used to add the plan option and allows different planType option
	 * 
	 * @param planRequestDto
	 * @return ResponseDto Displays the  status
	 *         code and message
	 */
	@Override 
	public ResponseDto addPlan(PlanRequestDto planRequestDto) throws ParseException {
		Plan plan = new Plan(); 
		if (ObjectUtils.isEmpty(planRequestDto)) {
			logger.error(ApplicationConstant.NO_ENTRY_FOUND);
			throw new NoEntriesException(ApplicationConstant.NO_ENTRY_FOUND);
		}
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime from = LocalDateTime.parse(planRequestDto.getFromTime(), dateFormatter);
		LocalDateTime to = LocalDateTime.parse(planRequestDto.getToTime(), dateFormatter);
		validatePlanTiming(from, to);
		plan.setFromTime(from);
		plan.setToTime(to);
		plan.setPlanType(planRequestDto.getPlanType());
		Plan savedplan=planRepository.save(plan);
		Slot slot= new Slot();
		slot.setFromTime(from);
		slot.setToTime(to);
		slot.setPlanId(savedplan.getPlanId());
		slot.setStatus(ApplicationConstant.AVAILABLE);
		slotRepository.save(slot);
		ResponseDto response = new ResponseDto();
		response.setMessage(ApplicationConstant.PLAN_ADDED_SUCCESSFULLY);
		response.setStatusCode(HttpStatus.ACCEPTED.value());
		return response;
	}
	/**
	 * @author Raghib
	 * 
	 *         Method is used to get all the plan option 
	 * 
	 * @return PlanResponseDto Displays the  planDetails
	 *         code and message
	 */
	public void validatePlanTiming(LocalDateTime fromTime, LocalDateTime toTime) {
		List<Plan> plans = planRepository.findAll();
		plans.forEach(plan -> {
			if (fromTime.isAfter(plan.getFromTime()) && fromTime.isBefore(plan.getToTime())) {
				throw new AlreadyTakenException(ApplicationConstant.SLOTS_ALREADY_BOOKED);
			}
			else if(fromTime.isAfter(toTime)){
				throw new TimeNotAllowed(ApplicationConstant.UNEXPECTED_TIME);
			}
		});
	} 
	@Override
	public PlanResponseDto getAllPlans() throws PlanNotFoundException {
		List<Plan> plansList = planRepository.findAll();

		List<PlanDetails> list = new ArrayList<PlanDetails>();
		
		plansList.forEach(plans->{
			PlanDetails planDetails = new PlanDetails();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			planDetails.setFromTime(plans.getFromTime().format(formatter).toString());
			planDetails.setPlanId(plans.getPlanId());
			planDetails.setPlanType(plans.getPlanType());
			planDetails.setToTime(plans.getToTime().format(formatter).toString());
			list.add(planDetails);
		});
		PlanResponseDto planResponseDto = new PlanResponseDto();
		planResponseDto.setPlanDetails(list);
		planResponseDto.setStatusCode(ApplicationConstant.SUCCESS_CODE);
		if (plansList.size() == 0) {
			planResponseDto.setStatusCode(ApplicationConstant.PLANS_NOT_FOUND);
			throw new PlanNotFoundException(ApplicationConstant.NO_PLANS);
		}
		return planResponseDto;
	}
}
