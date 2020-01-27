package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Employee;

public interface EmployeeDao {

	void readDataFile();
	void printAllData(List<Employee> employeeList);

}
