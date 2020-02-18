package com.geekyants.ads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geekyants.ads.dto.ResponseDto;
import com.geekyants.ads.dto.SlotRequestDto;
import com.geekyants.ads.dto.SlotResponseDto;
import com.geekyants.ads.exception.InvalidDataException;
import com.geekyants.ads.service.SlotService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class SlotController {
	
	@Autowired
	private SlotService slotService;
	/**
	 * @author Karthika T
	 * 
	 *         Method is used to Book the Slot 
	 *         
	 * @param planId , SlotRequestDto
	 * @return ResponseDto Displays the  status
	 *         code and message
	 */
	@PostMapping("/plans/{planId}/slots")
	public ResponseEntity<ResponseDto> bookSlot(@PathVariable Long planId,@RequestBody SlotRequestDto slotRequest){
		if((ObjectUtils.isEmpty(planId))&&(ObjectUtils.isEmpty(slotRequest))){
			throw new InvalidDataException("Invalid Request");
		}
		ResponseDto slotResponse=slotService.bookSlot(planId, slotRequest);
		return new ResponseEntity<ResponseDto>(slotResponse, HttpStatus.OK);
		
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
	@GetMapping("/plans/{planId}/slots")
	public ResponseEntity<SlotResponseDto> getSlots(@PathVariable Long planId){
		if((ObjectUtils.isEmpty(planId))){
			throw new InvalidDataException("Invalid Request");
		}
		SlotResponseDto slotResponse=slotService.getSlot(planId);
		return new ResponseEntity<SlotResponseDto>(slotResponse, HttpStatus.OK);
		
	}

}
