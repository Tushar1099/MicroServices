package com.course.departmentservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.departmentservice.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long>{
	
	Department findByDepartmentCode(String departmentCode);
}
