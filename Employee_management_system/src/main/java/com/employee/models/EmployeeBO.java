package com.employee.models;

public class EmployeeBO {
	public double dA( double basicPay) {
	
		double da=0.4*basicPay;
		return da;
	}
	public double hRA(double basicPay) {
		double hra=0.3*basicPay;
		return hra;
	}
	
	public double grossSal(double basicPay, double hra, double da) {
		double grossSalary=basicPay+hra+da;
		return grossSalary;
	}
	public double tax(double basicPay,double grossSalary) {
		double tx=0;
		if(grossSalary > 500000 && grossSalary<1000000) {
			tx=0.1*grossSalary;
		}
		else if(grossSalary>1000000 && grossSalary<2000000) {
			tx=0.2*grossSalary;
		}
		else if(grossSalary>2000000) {
			tx=0.3*grossSalary;
		}

		return tx;
	}
	public double net(double gross,double tax) {
		return gross-tax;
	}
		
}