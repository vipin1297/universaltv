package com.geekyants.ads.service;

import com.geekyants.ads.dto.LoginResponseDto;
import com.geekyants.ads.dto.LoginRequestDto;
import com.geekyants.ads.exception.NotFoundException;

public interface LoginService {

	LoginResponseDto authenticateCustomer(LoginRequestDto loginRequestDto) throws NotFoundException;

}
