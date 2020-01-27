package com.cognizant.dao;

public class Main {

	public static void main(String[] args) {

		EmployeeDao employeeDao = new EmployeeDaoImpl();
		employeeDao.readDataFile();
	}
}
