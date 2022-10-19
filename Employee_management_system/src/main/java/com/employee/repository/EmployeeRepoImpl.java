package com.employee.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.models.Employee;

public class EmployeeRepoImpl{
	@Autowired
	private EmployeeRepository repo1;
	
	public List<Employee> findEmployeeByName(String name){
		List<Employee> ListAll = (List<Employee>) repo1.findAll();
		List<Employee> nameList = new ArrayList<Employee>();
		for (Employee emp : ListAll) {
			if (emp.getName().equalsIgnoreCase(name))
				nameList.add(emp);
		}
		return nameList;
	}

	
	public Employee findEmployeeById(long id)
	{
		Employee employee=repo1.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
				
		
		return employee;
	}
	
	public void deleteEmployeeById(long id)
	{
		Employee employee=repo1.findById(id)
				.orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
		
		repo1.delete(employee);
	}
	
	public void insert(Employee emp) {
		repo1.save(emp);
	}
	
	public Employee updateEmployee(long id, Employee employee)
	{
		Employee updateEmployee=repo1.findById(id)
				.orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
		
		updateEmployee.setName(employee.getName());
		updateEmployee.setDateOfJoining(employee.getDateOfJoining());
		updateEmployee.setBasicPay(employee.getBasicPay());
		updateEmployee.setHRA(employee.getHRA());
		updateEmployee.setDA(employee.getDA());
		updateEmployee.setGrossSalary(employee.getGrossSalary());
		updateEmployee.setTax(employee.getTax());
		updateEmployee.setNet(employee.getNet());
		
		repo1.save(updateEmployee);

		return updateEmployee;
	}
	public List<Employee> findAll() {
		List<Employee> ListAll = (List<Employee>) repo1.findAll();
		return ListAll;
	}
    public void delete(Employee employee) {
		repo1.delete(employee);
	}
    
    public void deleteAll() {
    	repo1.deleteAll();
    }
}
