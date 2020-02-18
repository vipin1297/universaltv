package com.geekyants.ads.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import com.geekyants.ads.constant.ApplicationConstant;
import com.geekyants.ads.dto.ResponseDto;
import com.geekyants.ads.dto.SelectedSlot;
import com.geekyants.ads.dto.SlotDetail;
import com.geekyants.ads.dto.SlotRequestDto;
import com.geekyants.ads.dto.SlotResponseDto;
import com.geekyants.ads.entity.Plan;
import com.geekyants.ads.entity.Slot;
import com.geekyants.ads.exception.NotFoundException;
import com.geekyants.ads.exception.SlotNotAvailable;
import com.geekyants.ads.exception.TimeNotAllowed;
import com.geekyants.ads.repository.PlanRepository;
import com.geekyants.ads.repository.SlotRepository;
/*
 * Method is used to Book the Slolt and get all slots that are available
 */
@Service
public class SlotServiceImpl implements SlotService { 

	@Autowired
	SlotRepository slotRepository;
	
	@Autowired
	PlanRepository planRepository; 
	/**
	 * @author Karthika T
	 * 
	 *         Method is used to Book the Slot 
	 *         
	 * @param planId , SlotRequestDto
	 * @return ResponseDto Displays the  status
	 *         code and message
	 */
	@Transactional
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	public ResponseDto bookSlot(Long planId,SlotRequestDto slotRequest) {
		slotRequest.getSelectedSlots().forEach(slotreq->{
			Slot slot1= new Slot(); 
			DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String fromTime= slotreq.getFromTime();
			String toTime= slotreq.getToTime();
			LocalDateTime fromLocalDateTime=LocalDateTime.parse(fromTime, dateFormatter);
			LocalDateTime toLocalDateTime=LocalDateTime.parse(toTime, dateFormatter);
			if(fromLocalDateTime.isAfter(toLocalDateTime)){
				throw new TimeNotAllowed(ApplicationConstant.UNEXPECTED_TIME);
			}
			Slot newslot= new Slot();
			Optional<Plan> plan=planRepository.findById(planId) ;
			if(!(plan.isPresent())) {
				throw new NotFoundException(ApplicationConstant.PASS_NOT_FOUND);
			}
			List<Slot> slots=slotRepository.findByPlanIdAndStatus(planId, ApplicationConstant.AVAILABLE);
			List<Slot> bookedslots=slotRepository.findByPlanIdAndStatus(planId, ApplicationConstant.BOOKED);
			for(Slot s:slots) {
				for(Slot bs:bookedslots) {
					if(s.getFromTime().isEqual(bs.getFromTime())||s.getToTime().isEqual(bs.getFromTime())){
						throw new SlotNotAvailable(ApplicationConstant.SLOTS_ALREADY_BOOKED);
					}
				}
			}
			/*
			 * if(slots.isEmpty()) { if(fromLocalDateTime.isEqual(plan.get().getFromTime()))
			 * { newslot.setPlanId(planId); newslot.setFromTime(toLocalDateTime);
			 * newslot.setToTime(plan.get().getToTime());
			 * newslot.setStatus(ApplicationConstant.AVAILABLE);
			 * slotRepository.save(newslot);
			 * 
			 * } else if(!(fromLocalDateTime.isEqual(plan.get().getFromTime()))) {
			 * newslot.setPlanId(planId); newslot.setFromTime(plan.get().getFromTime());
			 * newslot.setToTime(fromLocalDateTime);
			 * newslot.setStatus(ApplicationConstant.AVAILABLE); newslot1.setPlanId(planId);
			 * newslot1.setFromTime(toLocalDateTime);
			 * newslot1.setToTime(plan.get().getToTime());
			 * newslot1.setStatus(ApplicationConstant.AVAILABLE);
			 * slotRepository.save(newslot); slotRepository.save(newslot1); } }
			 */
			slots.forEach(slot->{
				if(((fromLocalDateTime.isAfter(slot.getFromTime()) 
						|| fromLocalDateTime.isEqual(slot.getFromTime()))
						&&(( fromLocalDateTime.isBefore(slot.getToTime())
								||fromLocalDateTime.isEqual(slot.getToTime())) ))) {
					LocalDateTime availablefromTime=slot.getFromTime();
					LocalDateTime availableToTime=slot.getToTime();
					slot1.setPlanId(planId);
					slot1.setFromTime(fromLocalDateTime);
					slot1.setToTime(toLocalDateTime);
					slot1.setStatus(ApplicationConstant.BOOKED);
					slotRepository.save(slot1);
					if(fromLocalDateTime.isEqual(availablefromTime)) {
						slot.setFromTime(toLocalDateTime);
						slot.setToTime(availableToTime);
						slot.setStatus(ApplicationConstant.AVAILABLE);
						slotRepository.save(slot);
						
					}
					else if(!(fromLocalDateTime.isEqual(slot.getFromTime()))) {
						slot.setFromTime(slot.getFromTime());
						slot.setToTime(slot1.getFromTime());
						newslot.setPlanId(planId);
						newslot.setFromTime(slot1.getToTime());
						newslot.setToTime(availableToTime);
						newslot.setStatus(ApplicationConstant.AVAILABLE);
						try
						{
							Thread.sleep(5000);
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
						slotRepository.save(newslot);
					}
				}
			});
		});
		
		ResponseDto slotResponse= new ResponseDto();
		slotResponse.setStatusCode(ApplicationConstant.SUCCESS_CODE);
		slotResponse.setMessage("Slot booked");
		return slotResponse;
	}
	/**
	 * @author Karthika T
	 * 
	 *         Method is used to get the Slot 
	 *         
	 * @param planId 
	 * @return SlotResponseDto Displays the  SlotDetails
	 *         code and message
	 */
	@Override
	public SlotResponseDto getSlot(Long planId) {
		List<Slot> slots=slotRepository.findByPlanIdAndStatus(planId, ApplicationConstant.AVAILABLE);
		if(slots.isEmpty()) {
			throw new SlotNotAvailable(ApplicationConstant.SLOT_NOT_AVAILABLE);
		}
		SlotResponseDto slotResponseDto= new SlotResponseDto();
		List<SlotDetail> slotDetails= new ArrayList<SlotDetail>();
		slots.forEach(slot->{
			SlotDetail slotDetail= new SlotDetail();
			BeanUtils.copyProperties(slot, slotDetail);
			slotDetails.add(slotDetail);
		});
		slotResponseDto.setStatusCode(ApplicationConstant.SUCCESS_CODE);
		slotResponseDto.setSlotDetails(slotDetails);
		return slotResponseDto;
	}

}
