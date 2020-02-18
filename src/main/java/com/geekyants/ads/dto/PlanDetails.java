package com.geekyants.ads.dto;

public class PlanDetails {

	private String planType;
	private String fromTime;
	private String toTime;
	private Long planId;

	public String getPlanType() {
		return planType; 
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

}
