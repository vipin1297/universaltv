package com.geekyants.ads.dto;

import java.util.List;

public class PlanResponseDto {

	private Integer statusCode;
	private List<PlanDetails> planDetails;

	

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer successCode) {
		this.statusCode = successCode;
	}

	public List<PlanDetails> getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(List<PlanDetails> planDetails) {
		this.planDetails = planDetails;
	}

}
