package com.geekyants.ads.dto;

import java.time.LocalDateTime;

public class SlotDetail {
	
	private Long slotId;
	private LocalDateTime fromTime;
	private LocalDateTime toTime;
	private Long planId;
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Long getSlotId() {
		return slotId;
	}
	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}
	public LocalDateTime getFromTime() {
		return fromTime;
	}
	public void setFromTime(LocalDateTime fromTime) {
		this.fromTime = fromTime;
	}
	public LocalDateTime getToTime() {
		return toTime;
	}
	public void setToTime(LocalDateTime	 toTime) {
		this.toTime = toTime;
	}
	

}
