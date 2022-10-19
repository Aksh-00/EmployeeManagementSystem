package com.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>
{

	List<Employee> findByName(String name);
}
