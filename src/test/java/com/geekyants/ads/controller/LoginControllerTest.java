package com.geekyants.ads.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.geekyants.ads.dto.LoginRequestDto;
import com.geekyants.ads.dto.LoginResponseDto;
import com.geekyants.ads.exception.NotFoundException;
import com.geekyants.ads.service.LoginService;
import com.geekyants.ads.util.UniversalRoleEnum;



@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginControllerTest {

	@InjectMocks
	LoginController loginController;

	@Mock
	LoginService loginService;
	
	LoginRequestDto req = new LoginRequestDto();
	

	@Before
	public void before() {
		req.setMobileNumber("9513090305");
		req.setPassword("r@12345");
	}

	@Test
	public void authenticateCustomerTest() throws NotFoundException {
		
		LoginResponseDto res = new LoginResponseDto();
		res.setStatusCode(200);
		res.setRole(UniversalRoleEnum.Role.ADMIN);
		res.setUserName("Raghib");
		Mockito.when(loginService.authenticateCustomer(req)).thenReturn(new LoginResponseDto() );
		ResponseEntity<LoginResponseDto> response=loginController.authenticateCustomer(req);
		assertEquals(200, res.getStatusCode());
	}
	

}
