package com.geekyants.ads.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekyants.ads.dto.LoginResponseDto;
import com.geekyants.ads.dto.LoginRequestDto;
import com.geekyants.ads.exception.NotFoundException;
import com.geekyants.ads.service.LoginService;



@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
@RequestMapping("/login")

public class LoginController {
	
	/**
	 * This will inject all the implementations in the loginService.
	 */
	@Autowired
	LoginService loginService;

	/**
	 * @author Raghib
	 * 
	 *         this API is used to authenticate the User
	 * 
	 * @param LoginRequestDto for Admin and SalesPerson.
	 * @return LoginResponseDto
	 * @throws NotFoundException
	 * 
	 */
	
	@PostMapping
	public ResponseEntity<LoginResponseDto> authenticateCustomer(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		return new ResponseEntity(loginService.authenticateCustomer(loginRequestDto), HttpStatus.OK);
	}
	
	

}
