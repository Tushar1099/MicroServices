package com.course.departmentservice.service.impl;

import org.springframework.stereotype.Service;

import com.course.departmentservice.dto.DepartmentDto;
import com.course.departmentservice.entity.Department;
import com.course.departmentservice.mapper.DepartmentMapper;
import com.course.departmentservice.repo.DepartmentRepo;
import com.course.departmentservice.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepo departmentRepo;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		
		Department department = DepartmentMapper.mapToDepartment(departmentDto);

		Department savedDepartment = departmentRepo.save(department);

		DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);

		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {

		Department department = departmentRepo.findByDepartmentCode(departmentCode);

        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);

        return departmentDto;
	}

}
