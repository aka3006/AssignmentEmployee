package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Employee;

public class Main {

	public static void main(String[] args) {

		EmployeeDao employeeDao = new EmployeeDaoImpl();
		List<Employee> employeeList = employeeDao.readDataFile();
		System.out.println("Printing all employee details:\n");
		employeeDao.printAllData(employeeList);
		System.out.println("--------------------------------------------------------------------");
		System.out.println("\nPrinting employee count based on department:\n");
		employeeDao.printEmployeeCountOnDepartment(employeeList); 
		System.out.println("--------------------------------------------------------------------");
		System.out.println("\nPrinting employee with max and min salary in every department:\n");
		employeeDao.printEmployeeWithMaxMinSalary(employeeList);
		System.out.println("--------------------------------------------------------------------");
		System.out.println("\nPrinting average salary of each department in desending order:\n");
		employeeDao.printAverageSalaryOnDepartment(employeeList);
		System.out.println("--------------------------------------------------------------------");
	}
}
