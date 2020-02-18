package com.geekyants.ads.dto;

import javax.validation.constraints.NotBlank;

import com.geekyants.ads.constant.ApplicationConstant;

public class LoginRequestDto {
 	@NotBlank(message=ApplicationConstant.MOB_NOT_FOUND)
	private String mobileNumber;
	@NotBlank(message=ApplicationConstant.PASS_NOT_FOUND)
	private String password;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
