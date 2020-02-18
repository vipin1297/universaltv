package com.geekyants.ads.service;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.ObjectUtils;

import com.geekyants.ads.dto.PlanDetails;
import com.geekyants.ads.dto.PlanRequestDto;
import com.geekyants.ads.dto.PlanResponseDto;
import com.geekyants.ads.dto.ResponseDto;
import com.geekyants.ads.entity.Plan;
import com.geekyants.ads.exception.AlreadyTakenException;
import com.geekyants.ads.exception.PlanNotFoundException;
import com.geekyants.ads.repository.PlanRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PlanServiceImplTest {
	
	@InjectMocks
	PlanServiceImpl planServiceImpl;
	
	@Mock
	PlanRepository planRepository; 
	List<Plan> planlist = new ArrayList<Plan>();
	PlanResponseDto planResponseDto = new PlanResponseDto();
	Plan plan1 = new Plan();
	Plan plan2 = new Plan();
	Plan savedplan= null;
	@Before
	public void before() {
		savedplan = new Plan();
		
		plan1.setPlanId(1L);
		plan1.setPlanType("A");
		LocalDateTime fromTime = LocalDateTime.of(2020, Month.FEBRUARY, 17, 16, 00, 12);
		plan1.setFromTime(fromTime);
		LocalDateTime toTime = LocalDateTime.of(2020, Month.FEBRUARY, 17, 16, 40, 20);
		plan1.setToTime(toTime);
		plan2.setPlanId(2L);
		plan2.setPlanType("A");
		LocalDateTime fromTime2 = LocalDateTime.of(2020, Month.FEBRUARY, 17, 17, 00, 12);
		LocalDateTime toTime2 = LocalDateTime.of(2020, Month.FEBRUARY, 17, 17, 40, 20);
		plan2.setFromTime(fromTime2);
		plan2.setToTime(toTime2);
		planlist.add(plan1);
		planlist.add(plan2);
		savedplan.setFromTime(fromTime);
		savedplan.setToTime(toTime);
		savedplan.setPlanId(1L);
		savedplan.setPlanType("Gold");

	}
	 
	/*
	 * @Test public void addPlanTest() throws ParseException { Plan plan = new
	 * Plan(); ResponseDto response = new ResponseDto(); PlanRequestDto
	 * planRequestDto = new PlanRequestDto();
	 * planRequestDto.setFromTime("2020-02-17 20:00:00");
	 * planRequestDto.setToTime("2020-02-17 20:10:00");
	 * planRequestDto.setPlanType("Gold"); LocalDateTime fromTime =
	 * LocalDateTime.of(2020,Month.FEBRUARY,17,16,00,12); LocalDateTime toTime =
	 * LocalDateTime.of(2020,Month.FEBRUARY,17,16,15,12);
	 * plan.setFromTime(fromTime); plan.setToTime(toTime); plan.setPlanId(1L);
	 * plan.setPlanType("Gold"); if(ObjectUtils.isEmpty(planRequestDto))
	 * Mockito.when(planRepository.save(plan)).thenReturn(savedplan); response =
	 * planServiceImpl.addPlan(planRequestDto); assertEquals(202,
	 * response.getStatusCode()); }
	 */
	@Test
	public void validatePlanTimingTest() {
		List<Plan> plan = new ArrayList<Plan>();
		Plan plan1 = new Plan();
		PlanRequestDto planRequestDto = new PlanRequestDto();
		planRequestDto.setFromTime("2020-02-17 20:00:00");
		planRequestDto.setToTime("2020-02-17 20:10:00");
		planRequestDto.setPlanType("Gold");
		LocalDateTime fromTime = LocalDateTime.of(2020,Month.FEBRUARY,17,16,00,12);
		LocalDateTime toTime = LocalDateTime.of(2020,Month.FEBRUARY,17,16,15,12);
		plan1.setFromTime(fromTime);
		plan1.setToTime(toTime);
		plan1.setPlanId(1L);
		plan1.setPlanType("Gold");
		plan.add(plan1);
		Mockito.when(planRepository.findAll()).thenReturn((List<Plan>) plan);
		planServiceImpl.validatePlanTiming(fromTime, toTime);
		if (fromTime.isAfter(plan1.getFromTime()) && fromTime.isBefore(plan1.getToTime())) {
			throw new AlreadyTakenException("error");
		}
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
			Mockito.when(planRepository.findAll()).thenReturn(planlist);
			planResponseDto = planServiceImpl.getAllPlans();
			assertEquals(200, planResponseDto.getStatusCode());
		}

		@Test(expected = PlanNotFoundException.class)
		public void testGetPlansforPlansNotFoundEx() {
			List<Plan> list = new ArrayList<>();
			Mockito.when(planRepository.findAll()).thenReturn(list);
			planResponseDto = planServiceImpl.getAllPlans();
			assertEquals(200, planResponseDto.getStatusCode());

		}
	
	}



