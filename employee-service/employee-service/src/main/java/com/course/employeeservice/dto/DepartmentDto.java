package com.course.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DepartmentDto {

	private Long id;
	private String DepartmentName;
	private String departmentDescription;
	private String departmentCode;
}
