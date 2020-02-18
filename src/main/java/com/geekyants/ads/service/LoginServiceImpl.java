package com.geekyants.ads.service;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekyants.ads.constant.ApplicationConstant;
import com.geekyants.ads.dto.LoginResponseDto;
import com.geekyants.ads.dto.LoginRequestDto;
import com.geekyants.ads.entity.User;
import com.geekyants.ads.exception.NotFoundException;
import com.geekyants.ads.repository.UserRepository;



/**
 * 
 * @author Raghib
 * 
 *         Login Service has the implementations for Login related api's.
 * 
 *
 */

@Service 
public class LoginServiceImpl implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public LoginResponseDto authenticateCustomer(@Valid  LoginRequestDto loginRequestDto) throws NotFoundException {
		logger.info("LoginServiceImpl authenticateCustomer ---> autheticating customer");
		
		Optional<User> user = userRepository.findByMobileNumberAndPassword(loginRequestDto.getMobileNumber(),
				loginRequestDto.getPassword());

		if (!user.isPresent()) {
			logger.error("LoginServiceImpl authenticateCustomer ---> NotFoundException occured");
			throw new NotFoundException(ApplicationConstant.USER_NOT_FOUND);	
		}
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		BeanUtils.copyProperties(user.get(), loginResponseDto);		
		//loginResponseDto.setStatusCode(ApplicationConstant.SUCCESS_CODE);
		logger.info("LoginServiceImpl authenticateCustomer ---> Signed in");
		return loginResponseDto;
	}

}
