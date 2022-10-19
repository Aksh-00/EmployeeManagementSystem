package com.employee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employee.models.Employee;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long>
{
	
}
