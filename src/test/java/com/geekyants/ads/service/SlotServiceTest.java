package com.geekyants.ads.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.geekyants.ads.constant.ApplicationConstant;
import com.geekyants.ads.dto.SelectedSlot;
import com.geekyants.ads.dto.SlotResponseDto;
import com.geekyants.ads.entity.Plan;
import com.geekyants.ads.entity.Slot;
import com.geekyants.ads.exception.SlotNotAvailable;
import com.geekyants.ads.repository.PlanRepository;
import com.geekyants.ads.repository.SlotRepository;


@RunWith(MockitoJUnitRunner.Silent.class)
public class SlotServiceTest {

	
	SelectedSlot slotRequestDto= null;
	
	DateTimeFormatter dateTimeFormatter= null;
	
	@InjectMocks
	SlotServiceImpl slotServiceImpl;

	@Mock
	PlanRepository planRepository; 
	
	@Mock
	SlotRepository slotRepository;
	
	Plan plan= null;
	List<Slot> slots= null;
	Slot slot= null;
	Slot slot1= null;
	
	@Before
	public void before() {
		slotRequestDto= new SelectedSlot();
		
		slotRequestDto.setFromTime("2020-02-17 21:05:00");
		slotRequestDto.setToTime("2020-02-17 21:06:00");
		dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		plan= new Plan();
		plan.setPlanId(2L);
		plan.setFromTime(LocalDateTime.parse("2020-02-17 21:15:00", dateTimeFormatter));
		plan.setToTime(LocalDateTime.parse("2020-02-17 21:15:00", dateTimeFormatter));
		plan.setPlanType("premium");
		slot = new Slot();
		slots =new ArrayList<Slot>();
		slot.setFromTime(LocalDateTime.parse("2020-02-17 21:00:00", dateTimeFormatter));
		slot.setToTime(LocalDateTime.parse("2020-02-17 21:01:00", dateTimeFormatter));
		slot.setPlanId(2L);
		slot.setSlotId(1L);
		slot.setStatus(ApplicationConstant.AVAILABLE);
		slot1 = new Slot();
		slot1.setFromTime(LocalDateTime.parse("2020-02-17 21:03:00", dateTimeFormatter));
		slot1.setToTime(LocalDateTime.parse("2020-02-17 21:04:00", dateTimeFormatter));
		slot1.setPlanId(2L);
		slot1.setSlotId(2L);
		slot1.setStatus(ApplicationConstant.AVAILABLE);
		slots.add(slot);
		slots.add(slot1);
		Mockito.when(slotRepository.findByPlanIdAndStatus(17L,"available")).thenReturn(slots);
	}
	
	@Test
	public void bookSlotTest_positive()
	{
		Mockito.when(planRepository.findById(2L)).thenReturn(Optional.of(plan));
		Mockito.when(slotRepository.findByPlanIdAndStatus(2L,"available")).thenReturn(null);
		//ResponseDto response=slotServiceImpl.bookSlot(2L, slotRequestDto);
		//assertEquals(200, response.getStatusCode());
	}
	@Test(expected= SlotNotAvailable.class)
	public void getSlotTest_positive()
	{
		Mockito.when(slotRepository.findByPlanIdAndStatus(17L,"available")).thenReturn(slots);
		SlotResponseDto slotResponse=slotServiceImpl.getSlot(2L);
		
	}
	
}
