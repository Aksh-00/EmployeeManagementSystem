package com.employee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.employee.models.Employee;

public interface EmployeeService {

	public ArrayList<Employee> listAllEmployees();
	
	public List<Employee> findEmployeeByName(String name);
	
	public void insertEmployee(Employee employee);
	
	public ResponseEntity<Employee> findOneEmployee(long id);
	
	public void deleteEmployeeById(long id);
	
	public void deleteAllEmployees();
	
	public ResponseEntity<Employee> updateEmployee(long id,Employee employee);
}