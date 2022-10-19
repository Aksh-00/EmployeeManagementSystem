package com.employee.controllers;

//import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.models.Employee;
import com.employee.models.EmployeeBO;
import com.employee.services.EmployeeService;



@RestController
@RequestMapping("/employees")
public class EmployeeController 
{

		@Autowired
		private EmployeeService service;
		
		@PostMapping("/insertEmployee")
		public ResponseEntity<Employee>insertEmployee(@RequestBody Employee employee)
		{
			service.insertEmployee(employee);
			EmployeeBO emp=new EmployeeBO();
			double basic=employee.getBasicPay();
			long id=employee.getId();
			double hra=emp.hRA(basic);
			double da=emp.dA(basic);
			double gross=emp.grossSal(basic, hra, da);
			double tax=emp.tax(gross, basic);
			double net=emp.net(gross, tax);
			Employee update=new Employee();
			update.setBasicPay(basic);
			update.setName(employee.getName());
			update.setDateOfJoining(employee.getDateOfJoining());
			update.setId(employee.getId());
			update.setGrossSalary(gross);
			update.setHRA(hra);
			update.setDA(da);
			update.setTax(tax);
			update.setNet(net);
			return service.updateEmployee(id, update);
		}


	@GetMapping("/showAll")
	public ArrayList<Employee> listAllEmployees()
	{
		return service.listAllEmployees();
	}

	//find employee by name
	@GetMapping("/findByName/{name}")
	public List<Employee> findEmployeeByName(@PathVariable(value="name") String name)
	{
		return service.findEmployeeByName(name);
	}
	
	@GetMapping("/findByID/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
		return service.findOneEmployee(id);
	}
//	//Create employee method
//	@PostMapping
//	public Employee insertEmployee(@RequestBody Employee employee)
//	{
//		return repo.save(employee);
//	}

//	//get employee by id REST API
//	@GetMapping("/{id}")
//	public ResponseEntity<Employee> findOneEmployee(@PathVariable long id)
//	{
//		Employee employee=repo.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
//
//
//		return ResponseEntity.ok(employee);
//	}

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
//	@DeleteMapping("/{id}")
//	public Employee deleteEmployee(@PathVariable long id)
//	{
//		Employee employee=repo.findById(id)
//				.orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
//
//		repo.delete(employee);
//		return employee;
//
//	}
	@DeleteMapping("employees/{id}")
	public void deleteEmployeeById (@PathVariable int id) {
		service.deleteEmployeeById(id);
	}
	
	@DeleteMapping("/employees")
	public void deleteAllEmployees() {
		service.deleteAllEmployees();
	}
	
	@PutMapping("/update/{id}")
	public void updateEmployee(@PathVariable(value="id") long id,@RequestBody Employee employee) {
		service.updateEmployee(id, employee);
	}

}

