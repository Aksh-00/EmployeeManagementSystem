package com.employee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.models.Employee;
import com.employee.repository.EmployeeRepoImpl;


@Service
public class EmployeeService
{

	@Autowired
	private EmployeeRepoImpl repo;
	
	//List all employees in table

	public ArrayList<Employee> listAllEmployees()
	{
		return (ArrayList<Employee>) repo.findAll();
	}
	
	//find employee using name

	public List<Employee> findEmployeeByName(String name)
	{
	    return repo.findEmployeeByName(name);
	}
	
	//insert an employee into table
	public ResponseEntity<Employee> insertEmployee(Employee employee)
	{
		repo.insert(employee);
		return ResponseEntity.ok(employee);
	}


	//find employee using id

	public Employee findOneEmployee(long id)
	{
		Employee employee=repo.findEmployeeById(id);
				
		
		return employee;
	}
 
	//delete employee using id

		public void deleteEmployeeById(long id)
		{
			Employee employee=repo.findEmployeeById(id);
			repo.delete(employee);
			
		}
		
		//delete all employees

		public void deleteAllEmployees() {
			repo.deleteAll();
		}
		
		//update employee details
	
		public ResponseEntity<Employee> updateEmployee(long id, Employee employee)
		{
			System.out.println("Calling update repo");
			Employee update=repo.updateEmployee(id, employee);

			return ResponseEntity.ok(update);
		}
		
}
