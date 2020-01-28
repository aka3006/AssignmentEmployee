package com.cognizant.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cognizant.model.Employee;
import com.cognizant.model.Employee.Gender;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public List<Employee> readDataFile() {
		String file = "MOCK_DATA.csv";
		File mockDataFile = new File(file);
		BufferedReader reader = null;
		List<Employee> employeeList = new ArrayList<>();
		try {
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
					if ((data[3].substring(data[3].lastIndexOf(".") + 1)).length() == 2) {
						employee.setCountryDomain(true);
					}
					if (data[4].equalsIgnoreCase("male")) {
						employee.setGender(Gender.Male);
					} else if (data[4].equalsIgnoreCase("female")) {
						employee.setGender(Gender.Female);
					}
					employee.setDepartmentName(data[5]);
					employee.setSalary(Float.parseFloat(data[6].substring(1).replace(",", "")));
					employee.setSsn(data[7]);
					employee.setStreetAddress(data[8]);
					employee.setCity(data[9]);
					employee.setState(data[10]);
					employee.setZip(data[11]);
					employeeList.add(employee);
				}
			}
			return employeeList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return employeeList;
	}

	@Override
	public void printAllData(List<Employee> employeeList) {
		String outputFile = "OUTPUT.txt";
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter offset: ");
			int offset = scanner.nextInt();
			System.out.println("Enter limit: ");
			int limit = scanner.nextInt();
			scanner.close();
			employeeList.stream().limit(limit).forEach(System.out::println);

			PrintWriter outputStream = new PrintWriter(outputFile);
			outputStream.println(String.format("%1s %30s %30s %30s %30s %80s \r\n", "Employee Id", "Employee Name",
					"Gender", "Department Name", "Salary", "Address"));

			employeeList.stream().limit(limit).forEach(employee -> {
				outputStream.println(String.format("%1s %30s %30s %30s %30s %80s", employee.getEmployeeId(),
						employee.getFirstName() + " " + employee.getLastName(), employee.getGender(),
						employee.getDepartmentName(), employee.getSalary(),
						employee.getStreetAddress() + ", " + employee.getCity() + ", " + employee.getState()));
			});
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
