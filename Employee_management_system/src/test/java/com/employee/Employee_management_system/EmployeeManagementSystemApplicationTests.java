package com.employee.Employee_management_system;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.employee.models.Employee;
import com.employee.models.EmployeeBO;


@SpringBootTest
class EmployeeManagementSystemApplicationTests {
	public EmployeeBO ebo=new EmployeeBO();
	public Employee e=new Employee();
	
	@Before
	public void setUp() {
		System.out.println("Setting up tests");
	}
	
	@Test
	void contextLoads() {
		
	}
	
	@Test
	public void TestDa() {
		Assert.assertEquals(240000.0, ebo.dA(600000.0), 0.000001d);
		Assert.assertEquals(200000.0, ebo.dA(500000.0), 0.000001d);
		Assert.assertEquals(280000.0, ebo.dA(700000.0), 0.000001d);
		Assert.assertEquals(400000.0, ebo.dA(1000000.0), 0.000001d);
		Assert.assertEquals(600000.0, ebo.dA(1500000.0), 0.000001d);		
	}
	
	@Test
	public void TestHra() {
		Assert.assertEquals(180000.0, ebo.hRA(600000.0), 0.000001d);
		Assert.assertEquals(150000.0, ebo.hRA(500000.0), 0.000001d);
		Assert.assertEquals(210000.0, ebo.hRA(700000.0), 0.000001d);
		Assert.assertEquals(300000.0, ebo.hRA(1000000.0), 0.000001d);
		Assert.assertEquals(450000.0, ebo.hRA(1500000.0), 0.000001d);
	}
	
	@Test
	public void TestGross() {
		Assert.assertEquals(1020000.0, ebo.grossSal(600000.0, 180000.0, 240000.0),0.00001d);
		Assert.assertEquals(850000.0, ebo.grossSal(500000.0, 150000.0, 200000.0),0.00001d);
		Assert.assertEquals(1190000.0, ebo.grossSal(700000.0, 210000.0, 280000.0),0.00001d);
		Assert.assertEquals(1700000.0, ebo.grossSal(1000000.0, 300000.0,400000.0),0.00001d);
		Assert.assertEquals(2550000.0, ebo.grossSal(1500000.0, 450000.0,600000.0),0.00001d);
	}
	
	@Test
	public void TestTax() {
		Assert.assertEquals(204000.0, ebo.tax(600000.0, 1020000.0), 0.00001d);
		Assert.assertEquals(85000.0, ebo.tax(500000.0, 850000.0), 0.00001d);
		Assert.assertEquals(238000.0, ebo.tax(700000.0, 1190000.0), 0.00001d);
		Assert.assertEquals(340000.0, ebo.tax(1000000.0, 1700000.0), 0.00001d);
		Assert.assertEquals(675000.0, ebo.tax(1500000.0, 2250000.0), 0.00001d);
	}
	
	@Test
	public void TestNet() {
		Assert.assertEquals(816000.0, ebo.net(1020000,204000.0), 0.00001d);
		Assert.assertEquals(765000.0, ebo.net(850000.0, 85000.0), 0.00001d);
		Assert.assertEquals(952000.0, ebo.net(1190000.0, 238000.0), 0.00001d);
		Assert.assertEquals(1360000.0, ebo.net(1700000.0, 340000.0), 0.00001d);
		Assert.assertEquals(1875000.0, ebo.net(2550000.0, 675000.0), 0.00001d);
	}
	
	@After
	public void tearDown() {
		ebo=null;
		e=null;
	}
}
