package com.employee.models;

public class EmployeeBO {//Class implementing the business logic

	public double dA( double basicPay) {//DA Calculation
	
		double da=0.4*basicPay;
		return da;
	}
	public double hRA(double basicPay) {//HRA Calculation
		double hra=0.3*basicPay;
		return hra;
	}
	
	public double grossSal(double basicPay, double hra, double da) {//Gross Salary Calculation
		double grossSalary=basicPay+hra+da;
		return grossSalary;
	}
	public double tax(double basicPay,double grossSalary) {//Tax Calculation
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
	public double net(double gross,double tax) {//Net Calculation
		return gross-tax;
	}
		
}