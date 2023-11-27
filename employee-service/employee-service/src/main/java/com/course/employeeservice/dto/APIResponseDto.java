package com.course.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {

	private EmployeeDto empdto;
	private DepartmentDto deptdto;
	private OrganizationDto organizationDto;
}
