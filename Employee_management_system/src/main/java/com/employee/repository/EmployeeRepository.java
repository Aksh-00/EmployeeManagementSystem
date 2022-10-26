package com.employee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employee.models.Employee;
//Provides contract for EmployeeRepoImpl.java to implement
@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long>
{
	
}
