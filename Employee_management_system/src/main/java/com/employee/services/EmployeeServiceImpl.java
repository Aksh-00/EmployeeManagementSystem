package com.employee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.models.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

	@Autowired
	private EmployeeRepository repo;
	
	//List all employees in table
	@Override
	public ArrayList<Employee> listAllEmployees()
	{
		return (ArrayList<Employee>) repo.findAll();
	}
	
	//find employee using name
	@Override
	public List<Employee> findEmployeeByName(String name)
	{
	    return (List<Employee>) repo.findByName(name);
	}
	
	//insert an employee into table
	@Override
	public void insertEmployee(Employee employee)
	{
		this.repo.save(employee);
	}

	//find employee using id
	@Override
	public ResponseEntity<Employee> findOneEmployee(long id)
	{
		Employee employee=repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
				
		
		return ResponseEntity.ok(employee);
	}
 
	//delete employee using id
		@Override
		public void deleteEmployeeById(long id)
		{
			Employee employee=repo.findById(id)
					.orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
			
			repo.delete(employee);
			
		}
		
		//delete all employees
		@Override
		public void deleteAllEmployees() {
			repo.deleteAll();
		}
		
		//update employee details
		@Override
		public ResponseEntity<Employee> updateEmployee(long id, Employee employee)
		{
			Employee updateEmployee=repo.findById(id)
					.orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
			
			updateEmployee.setName(employee.getName());
			updateEmployee.setDateOfJoining(employee.getDateOfJoining());
			updateEmployee.setBasicPay(employee.getBasicPay());
			updateEmployee.setHRA(employee.getHRA());
			updateEmployee.setDA(employee.getDA());
			updateEmployee.setGrossSalary(employee.getGrossSalary());
			updateEmployee.setTax(employee.getTax());
			updateEmployee.setNet(employee.getNet());
			
			repo.save(updateEmployee);

			return ResponseEntity.ok(updateEmployee);
		}
		
}
