package com.cognizant.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {


	@Override
	public void readDataFile() {
		String file = "MOCK_DATA.csv";
		File mockDataFile = new File(file);
		BufferedReader reader = null;
		try {
			List<Employee> employeeList = new ArrayList<>();
			String line = "";
			reader = new BufferedReader(new FileReader(mockDataFile));
			reader.readLine();

			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length > 0) {
					Employee employee = new Employee();

					employee.setEmployeeId(data[0]);
					employee.setFirstName(data[1]);
					employee.setLastName(data[2]);
					employee.setEmail(data[3]);
					employee.setGender(data[4]);
					employee.setDepartmentName(data[5]);
					employee.setSalary((data[6]));
					employee.setSsn(data[7]);
					employee.setStreetAddress(data[8]);
					employee.setCity(data[9]);
					employee.setState(data[10]);
					employee.setZip(data[11]);
					employeeList.add(employee);
				}
			}
			employeeList.forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void printAllData(List<Employee> employeeList) {

	}

}
