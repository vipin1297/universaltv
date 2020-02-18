package com.geekyants.ads.dto;

import java.util.List;

public class SlotResponseDto {
	
	private Integer statusCode;
	
	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	private List<SlotDetail> slotDetails;

	public List<SlotDetail> getSlotDetails() {
		return slotDetails;
	}

	public void setSlotDetails(List<SlotDetail> slotDetails) {
		this.slotDetails = slotDetails;
	}
	

}
