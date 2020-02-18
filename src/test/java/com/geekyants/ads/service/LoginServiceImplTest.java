package com.geekyants.ads.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.geekyants.ads.dto.LoginRequestDto;
import com.geekyants.ads.dto.LoginResponseDto;
import com.geekyants.ads.entity.User;
import com.geekyants.ads.exception.NotFoundException;
import com.geekyants.ads.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginServiceImplTest {

	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	@Mock
	UserRepository userRepository;
	
	LoginRequestDto req = new LoginRequestDto();
	User user = new User();

	@Before
	public void before() {
		req.setMobileNumber("9513090305");
		req.setPassword("r@12345");
		user.setUserId(1L);

	}

	@Test
	public void authenticateCustomer() throws NotFoundException {

		Mockito.when(userRepository.findByMobileNumberAndPassword(req.getMobileNumber(), req.getPassword()))
				.thenReturn(Optional.of(user));
		LoginResponseDto resp = loginServiceImpl.authenticateCustomer(req);
		assertNotNull(resp);
	}

	@Test(expected = NotFoundException.class)
	public void testUserLoginForUserNotFoundEx() throws NotFoundException {

		Mockito.when(userRepository.findByMobileNumberAndPassword(req.getMobileNumber(), req.getPassword()))
				.thenReturn(Optional.ofNullable(null));
		loginServiceImpl.authenticateCustomer(req);

	}

}
