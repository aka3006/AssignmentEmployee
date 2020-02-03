package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Employee;

public interface EmployeeDao {

	public List<Employee> readDataFile();

	public void printAllData(List<Employee> employeeList);
	
	public void printEmployeeCountOnDepartment(List<Employee> employeeList); 
	
	public void printEmployeeWithMaxMinSalary(List<Employee> employeeList);
	
	public void printAverageSalaryOnDepartment(List<Employee> employeeList);
	
}
