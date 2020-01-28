package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Employee;

public class Main {

	public static void main(String[] args) {

		EmployeeDao employeeDao = new EmployeeDaoImpl();
		List<Employee> employeeList = employeeDao.readDataFile();
		employeeDao.printAllData(employeeList);
	}
}
