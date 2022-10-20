package com.employee.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.models.Employee;

@Repository
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
	
	public Employee insert(Employee emp) {
		repo1.save(emp);
		return emp;
	}
	
	public Employee updateEmployee(long id, Employee employee)
	{
		System.out.println("Inside repo");
		Employee updateEmploye=repo1.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found with id: "+id));
		updateEmploye.setName(employee.getName());
		updateEmploye.setDateOfJoining(employee.getDateOfJoining());
		updateEmploye.setBasicPay(employee.getBasicPay());
		updateEmploye.setHRA(employee.getHRA());
		updateEmploye.setDA(employee.getDA());
		updateEmploye.setGrossSalary(employee.getGrossSalary());
		updateEmploye.setTax(employee.getTax());
		updateEmploye.setNet(employee.getNet());
		repo1.save(updateEmploye);
		System.out.println("Updated");
		return updateEmploye;
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
