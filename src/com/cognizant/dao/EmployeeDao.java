package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Employee;

public interface EmployeeDao {

	public List<Employee> readDataFile();

	public void printAllData(List<Employee> employeeList);

}
