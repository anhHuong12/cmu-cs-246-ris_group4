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

class EMP_PARTTIME extends EMPLOYEE{
	int numberOfWorkdays;
	
	public int getNumberOfWorkdays() {
		return numberOfWorkdays;
	}

	public void setNumberOfWorkdays(int numberOfWorkdays) {
		this.numberOfWorkdays = numberOfWorkdays;
	}

	public void Input() {
		super.Input();
		Scanner scanner = new Scanner(System.in);
		System.out.print("enter number of workdays: ");
		this.setNumberOfWorkdays(scanner.nextInt());
	}
	
	public void Output() {
		super.Output();
		System.out.println("number of workdays: " + this.getNumberOfWorkdays());
	}
	
	public double CalculateSalary() {
		double salary = IEMPLOYEE.BASIC_SALARY * numberOfWorkdays/26;
		return salary;
	}
	
	public double CalculateAllowance() {
		if(this.CalculateSeniority() >= 10)
			return 500000;
		if(this.CalculateSeniority() >= 5)
			return 300000;
		return 0;
	}
}

class EMP_LIST{
	ArrayList <EMPLOYEE> list = new ArrayList<>();
	
	public void AddNew() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("enter type of employee (1 for full-time, 2 for part-time): ");
		int type = scanner.nextInt();
		
		EMPLOYEE employee;
		if(type == 1) {
			employee = new EMP_FULLTIME();
		}else if(type == 2) {
			employee = new EMP_PARTTIME();
		}else {
			System.out.println("invalid type of employee!");
			return;
		}
		
		employee.Input();
		AddNew();
		System.out.println("employee added successfully!");
	}
	
	public void sort() {
		Collections.sort(list, Comparator.comparingDouble(EMPLOYEE::CalculateSalary));
	}
	
	public double CalculateTotalSalary() {
		return list.stream().mapToDouble(EMPLOYEE::CalculateSalary).sum();
	}
	
	public void delete(int position) {
		if(position >=0 && position < list.size()) {
			list.remove(position);
		}else {
			System.out.println("invalid position.");
		}
	}
	
	public void Menu() {
		int choice;
		do {
			System.out.println("1. add new list of employee.");
			System.out.println("2. sort list of employee.");
			System.out.println("3. calculate the total salary of the employee.");
			System.out.println("4. delete a employee.");
			System.out.println("5. exit.");
			Scanner scanner = new Scanner(System.in);
			System.out.print("enter your choice: ");
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1:{
				AddNew();
				break;
			}
			case 2:{
				sort();
				break;
			}
			case 3:{
				System.out.println("total salary: " + CalculateTotalSalary());
				break;
			}
			case 4:{
				System.out.print("enter position to delete: ");
				int position = scanner.nextInt();
				delete(position);
				break;
			}
			case 5:{
				System.out.println("exiting.");
				break;
			}
			default:
				System.out.println("invalid choice!");
				break;
			}
		}while(choice != 5);
	}
}

