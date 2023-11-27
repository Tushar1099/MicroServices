package com.course.employeeservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.employeeservice.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmail(String email);
}
