package com.employee.controllers;

//import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.models.Employee;
import com.employee.repository.EmployeeRepository;
//import com.employee.services.EmployeeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/employees")
public class EmployeeController 
{

	//	@Autowired
	//	private EmployeeService service;
	//
	//	@PostMapping
	//	public String saveEmployee(@ModelAttribute("employee") Employee employee)
	//	{
	//		service.insertEmployee(employee);
	//		return "redirect:/";
	//	}

	@Autowired
	private EmployeeRepository repo;

	@GetMapping
	public ArrayList<Employee> listAllEmployees()
	{
		return (ArrayList<Employee>) repo.findAll();
	}

	//find employee by name
	@GetMapping("/find/{name}")
	public List<Employee> findEmployeeByName(@PathVariable(value="name") String name)
	{

		return (List<Employee>) repo.findByName(name);
	}

	//Create employee method
	@PostMapping
	public Employee insertEmployee(@RequestBody Employee employee)
	{
		return repo.save(employee);
	}

	//get employee by id REST API
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findOneEmployee(@PathVariable long id)
	{
		Employee employee=repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));


		return ResponseEntity.ok(employee);
	}

	//updated employee REST API
	//	@PutMapping("/{id}")
	//	public ResponseEntity<Employee> updateEmployee(@PathVariable(name="id") long id,@RequestBody Employee employee)
	//	{
	//		Employee updateEmployee=repo.findById(id)
	//				.orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
	//		
	//		updateEmployee.setFirstname(employee.getFirstname());
	//		updateEmployee.setLastname(employee.getLastname());
	//		updateEmployee.setEmailId(employee.getEmailId());
	//		repo.save(updateEmployee);
	//		
	//		return ResponseEntity.ok(updateEmployee);
	//	}


	//delete employee REST API
	@DeleteMapping("/{id}")
	public Employee deleteEmployee(@PathVariable long id)
	{
		Employee employee=repo.findById(id)
				.orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));

		repo.delete(employee);
		return employee;

	}

}

