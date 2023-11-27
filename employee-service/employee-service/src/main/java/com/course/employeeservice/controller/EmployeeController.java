package com.course.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.employeeservice.dto.APIResponseDto;
import com.course.employeeservice.dto.EmployeeDto;
import com.course.employeeservice.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("employee-data")
public class EmployeeController {

	private EmployeeService empService;
	
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto empDto){
		EmployeeDto savedEmp =  empService.saveEmployee(empDto);
		return new ResponseEntity<>(savedEmp,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("id") Long id){
		APIResponseDto apiResponseDto = empService.getEmployee(id);
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
}
