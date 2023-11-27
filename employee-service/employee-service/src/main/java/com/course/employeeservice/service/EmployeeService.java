package com.course.employeeservice.service;

import com.course.employeeservice.dto.APIResponseDto;
import com.course.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto saveEmployee(EmployeeDto empDto);
	
	APIResponseDto getEmployee(Long id);
}
