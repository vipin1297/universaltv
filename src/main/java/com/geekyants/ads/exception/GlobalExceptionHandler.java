package com.geekyants.ads.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.geekyants.ads.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(AlreadyTakenException.class)
	public ResponseEntity<ErrorDto> AlreadyTakenException(AlreadyTakenException ex) {

		ErrorDto errorResponse = new ErrorDto();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(0001);
		return new ResponseEntity<ErrorDto>(errorResponse, HttpStatus.OK);
	}
	@ExceptionHandler(EnterAppropriateTimeException.class)
	public ResponseEntity<ErrorDto> EnterAppropriateTimeException(EnterAppropriateTimeException ex) {

		ErrorDto errorResponse = new ErrorDto();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(0002);
		return new ResponseEntity<ErrorDto>(errorResponse, HttpStatus.OK);
	}
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDto> NotFoundException(NotFoundException ex) {

		ErrorDto errorResponse = new ErrorDto();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(0003);
		return new ResponseEntity<ErrorDto>(errorResponse, HttpStatus.OK);
	}
	@ExceptionHandler(NoEntriesException.class)
	public ResponseEntity<ErrorDto> NoEntriesException(NoEntriesException ex) {

		ErrorDto errorResponse = new ErrorDto();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(0004);
		return new ResponseEntity<ErrorDto>(errorResponse, HttpStatus.OK);
	}
	@ExceptionHandler(TimeNotAllowed.class)
	public ResponseEntity<ErrorDto> TimeNotAllowed(TimeNotAllowed ex) {

		ErrorDto errorResponse = new ErrorDto();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(0005);
		return new ResponseEntity<ErrorDto>(errorResponse, HttpStatus.OK);
	}
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ErrorDto> InvalidDataException(InvalidDataException ex) {

		ErrorDto errorResponse = new ErrorDto();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(0006);
		return new ResponseEntity<ErrorDto>(errorResponse, HttpStatus.OK);
	}
	@ExceptionHandler(SlotNotAvailable.class)
	public ResponseEntity<ErrorDto> SlotNotAvailable(SlotNotAvailable ex) {

		ErrorDto errorResponse = new ErrorDto();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(0007);
		return new ResponseEntity<ErrorDto>(errorResponse, HttpStatus.OK);
	}

}
