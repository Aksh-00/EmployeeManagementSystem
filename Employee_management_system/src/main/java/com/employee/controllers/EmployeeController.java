package com.employee.controllers;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
			System.out.println("Inserted");
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
			System.out.println("About to update");
			
			return service.updateEmployee(update.getId(), update);
		}


	@GetMapping("/showAll")
	public ArrayList<Employee> listAllEmployees()
	{
		return service.listAllEmployees();
	}

	@DeleteMapping("/employees/{id}")
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

