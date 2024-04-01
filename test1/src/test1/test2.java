package test1;

import java.util.*;

interface IEMPLOYEE{
	double BASIC_SALARY = 115000;
	
	double CalculateSalary();
	
	double CalculateAllowance();
}

abstract class EMPLOYEE implements IEMPLOYEE{
	String EmpID;
	String EmpName;
	String EmpDateOfBirth;
	int StartDate;

	public EMPLOYEE() {
		this.EmpID = "";
		this.EmpName = "";
		this.EmpDateOfBirth = "";
		this.StartDate = 0;
	}
	
	public EMPLOYEE(String empID, String empName, String empDateOfBirth, int startDate) {
		EmpID = empID;
		EmpName = empName;
		EmpDateOfBirth = empDateOfBirth;
		StartDate = startDate;
	}

	public String getEmpID() {
		return EmpID;
	}

	public void setEmpID(String empID) {
		EmpID = empID;
	}

	public String getEmpName() {
		return EmpName;
	}

	public void setEmpName(String empName) {
		EmpName = empName;
	}

	public String getEmpDateOfBirth() {
		return EmpDateOfBirth;
	}

	public void setEmpDateOfBirth(String empDateOfBirth) {
		EmpDateOfBirth = empDateOfBirth;
	}

	public int getStartDate() {
		return StartDate;
	}

	public void setStartDate(int startDate) {
		StartDate = startDate;
	}
	
	public void Input() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("enter id: ");
		this.setEmpID(scanner.next());
		scanner.nextLine();
		System.out.print("enter name: ");
		this.setEmpName(scanner.nextLine());
		System.out.print("enter date of birth: ");
		this.setEmpDateOfBirth(scanner.next());
		System.out.print("enter start date: ");
		this.setStartDate(scanner.nextInt());
	}
	
	public void Output() {
		System.out.println("id: " + this.getEmpID());
		System.out.println("name: " + this.getEmpName());
		System.out.println("date of birth: " + this.getEmpDateOfBirth());
		System.out.println("start date: " + this.getStartDate());
	}
	
	int CalculateSeniority() {
		return 2024 - this.getStartDate();
	}
	
	public double CalculateSalary() {
		return 0;
	}
	
	public double CalculateAllowance() {
		return 0;
	}
}

class EMP_FULLTIME extends EMPLOYEE{
	double coefficients;
	
	public double getCoefficients() {
		return coefficients;
	}

	public void setCoefficients(double coefficients) {
		this.coefficients = coefficients;
	}
	
	public void Input() {
		super.Input();
		Scanner scanner = new Scanner(System.in);
		System.out.print("enter coefficients: ");
		this.setCoefficients(scanner.nextDouble());
	}
	
	public void Output() {
		super.Output();
		System.out.println("coefficients: " + this.getCoefficients());
	}
	
	public double CalculateSalary() {
		double salary = IEMPLOYEE.BASIC_SALARY * this.coefficients + CalculateAllowance();
		return salary;
	}
	
	public double CalculateAllowance() {
		if(this.CalculateSeniority() >= 10)
			return 1000000;
		if(this.CalculateSeniority() >= 5)
			return 500000;
		return 0;
	}
}
