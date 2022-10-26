package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)//Throws exception when resource not found
public class EmployeeNotFoundException extends RuntimeException
{
	public EmployeeNotFoundException(String message)
	{
		super(message);
	}
}