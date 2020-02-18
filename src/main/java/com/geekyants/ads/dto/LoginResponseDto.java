package com.geekyants.ads.dto;

import com.geekyants.ads.util.UniversalRoleEnum.Role;

public class LoginResponseDto {

	private Integer statusCode;
	private String userName;
	private Role role;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode; 
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
