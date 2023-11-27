package com.course.employeeservice.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.course.employeeservice.dto.APIResponseDto;
import com.course.employeeservice.dto.DepartmentDto;
import com.course.employeeservice.dto.EmployeeDto;
import com.course.employeeservice.dto.OrganizationDto;
import com.course.employeeservice.entity.Employee;
import com.course.employeeservice.exception.EmailAlreadyExistException;
import com.course.employeeservice.exception.ResourceNotFoundException;
import com.course.employeeservice.repo.EmployeeRepo;
import com.course.employeeservice.service.APIClient;
import com.course.employeeservice.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepo empRepo;

	private ModelMapper modelMapper;
	
//	private RestTemplate restTemplate;
	
	private WebClient webClient;
	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto empDto) {

		Optional<Employee> optionalEmp = empRepo.findByEmail(empDto.getEmail());

		if (optionalEmp.isPresent()) {
			throw new EmailAlreadyExistException("Email already exists for this user");
		}

		Employee emp = modelMapper.map(empDto, Employee.class);

		Employee savedEmp = empRepo.save(emp);

		EmployeeDto savedEmpRepo = modelMapper.map(savedEmp, EmployeeDto.class);

		return savedEmpRepo;
	}

	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartmentResponse")
	@Override
	public APIResponseDto getEmployee(Long id) {
		
		
		Employee emp = empRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("name", "id", id)
		);
		

		Employee empId = empRepo.findById(id).get();
		
//		ResponseEntity<DepartmentDto> responseEntity =  restTemplate.getForEntity("http://localhost:8080/departments" + emp.getDepartmentCode(),DepartmentDto.class);
		
//		DepartmentDto deptDto = responseEntity.getBody();
		
		DepartmentDto deptDto = webClient.get()
				 .uri("http://localhost:8080/departments/"+empId.getDepartmentCode())
				 .retrieve()
				 .bodyToMono(DepartmentDto.class)
				 .block();

//		DepartmentDto deptDto = apiClient.getDeptByCode(emp.getDepartmentCode());
		
		EmployeeDto empDto = modelMapper.map(emp, EmployeeDto.class);
		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmpdto(empDto);
		apiResponseDto.setDeptdto(deptDto);

		return apiResponseDto;
	}
	
	public APIResponseDto getDefaultDepartmentResponse(Long employeeId, Exception ex) {
		Employee employee = empRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Employee id", employeeId));

//		EmployeeDto employeeDtoReturn = AutoEmployeeMapper.MAPPER.maptoEmployeeDto(employee);
		EmployeeDto empDto = modelMapper.map(employee, EmployeeDto.class);

		//ResponseEntity<DepartmentDto> response = restTemplate.getForEntity("http://localhost:8080/api/depart/bycode/"+employeeDtoReturn.getDepartmentCode(), DepartmentDto.class);
		//
		//DepartmentDto departmentDto = response.getBody();

//		DepartmentDto departmentDto = webClient.get()
//				.uri("http://localhost:8080/api/depart/bycode/" + employeeDtoReturn.getDepartmentCode()).retrieve()
//				.bodyToMono(DepartmentDto.class).block();
		
		DepartmentDto dptDto = new DepartmentDto(12L, "defaultDepartmentName", "Default Description", "DD01");
		OrganizationDto organizationDto = new OrganizationDto(12L, "defaultOrgName", "Default Description", "DD01",LocalDateTime.now());
		
//		DepartmentDto departmentDto = feignApiClient.getDepartmentById(employeeDtoReturn.getDepartmentCode());
		return new APIResponseDto(empDto, dptDto,organizationDto);
	}

}
