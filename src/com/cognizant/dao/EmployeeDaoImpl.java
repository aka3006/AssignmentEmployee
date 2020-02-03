package com.cognizant.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.cognizant.model.Employee;
import com.cognizant.model.Employee.Gender;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public List<Employee> readDataFile() {
		BufferedReader reader = null;
		List<Employee> employeeList = new ArrayList<>();

		try {
			InputStream inputStream = new FileInputStream(new File("MOCK_DATA.csv"));
			reader = new BufferedReader(new InputStreamReader(inputStream));
			reader.readLine();
			employeeList = reader.lines().map(line -> line.split(",")).map(line -> {
				Employee employee = new Employee();
				employee.setEmployeeId(line[0]);
				employee.setFirstName(line[1]);
				employee.setLastName(line[2]);
				employee.setEmail(line[3]);
				if ((line[3].substring(line[3].lastIndexOf(".") + 1)).length() == 2) {
					employee.setCountryDomain(true);
				}
				if (line[4].equalsIgnoreCase("male")) {
					employee.setGender(Gender.Male);
				} else if (line[4].equalsIgnoreCase("female")) {
					employee.setGender(Gender.Female);
				}
				employee.setDepartmentName(line[5]);
				employee.setSalary(Float.parseFloat(line[6].substring(1).replace(",", "")));
				employee.setSsn(line[7]);
				employee.setStreetAddress(line[8]);
				employee.setCity(line[9]);
				employee.setState(line[10]);
				employee.setZip(line[11]);
				return employee;
			}).collect(Collectors.toList());
			reader.close();
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
		/* String outputFile = "OUTPUT.txt"; */
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter offset: ");
			int offset = scanner.nextInt();
			System.out.println("Enter limit: ");
			int limit = scanner.nextInt();
			scanner.close();
			System.out.println(String.format("%1s %30s %30s %30s %30s %80s \r\n", "Employee Id", "Employee Name",
					"Gender", "Department Name", "Salary", "Address"));
			employeeList.stream().limit(limit).forEach(employee -> {
				System.out.println(String.format("%1s %30s %30s %30s %30s %80s", employee.getEmployeeId(),
						employee.getFirstName() + " " + employee.getLastName(), employee.getGender(),
						employee.getDepartmentName(), employee.getSalary(),
						employee.getStreetAddress() + ", " + employee.getCity() + ", " + employee.getState()));
			});

			/*
			 * PrintWriter outputStream = new PrintWriter(outputFile);
			 * outputStream.println(String.format("%1s %30s %30s %30s %30s %80s \r\n",
			 * "Employee Id", "Employee Name", "Gender", "Department Name", "Salary",
			 * "Address"));
			 * 
			 * employeeList.stream().limit(limit).forEach(employee -> {
			 * outputStream.println(String.format("%1s %30s %30s %30s %30s %80s",
			 * employee.getEmployeeId(), employee.getFirstName() + " " +
			 * employee.getLastName(), employee.getGender(), employee.getDepartmentName(),
			 * employee.getSalary(), employee.getStreetAddress() + ", " + employee.getCity()
			 * + ", " + employee.getState())); }); outputStream.flush();
			 * outputStream.close();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void printEmployeeCountOnDepartment(List<Employee> employeeList) {
		Map<String, Long> employeeCountMap = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartmentName, Collectors.counting()));
		employeeCountMap.entrySet().stream().forEach(System.out::println);
	}

	@Override
	public void printEmployeeWithMaxMinSalary(List<Employee> employeeList) {
		Map<String, Optional<Employee>> employeeWithMaxSalary = employeeList.stream().collect(Collectors.groupingBy(
				Employee::getDepartmentName, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
		System.out.println("Max salary: \n");
		employeeWithMaxSalary.entrySet().stream().forEach(employee -> {
			System.out.println(employee.getKey());
			System.out.println(employee.getValue());
		});
		
		Map<String, Optional<Employee>> employeeWithMinSalary = employeeList.stream().collect(Collectors.groupingBy(
				Employee::getDepartmentName, Collectors.minBy(Comparator.comparingDouble(Employee::getSalary))));
		System.out.println("\nMin salary: \n");
		employeeWithMinSalary.entrySet().stream().forEach(employee -> {
			System.out.println(employee.getKey());
			System.out.println(employee.getValue());
		});
	}

	@Override
	public void printAverageSalaryOnDepartment(List<Employee> employeeList) {
		Map<String, Double> averageSalaryOnDepartmentMap = employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartmentName, Collectors.averagingDouble(Employee::getSalary)));
		Map<String, Double> sortedAverageSalaryOnDepartmentMap = averageSalaryOnDepartmentMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		sortedAverageSalaryOnDepartmentMap.entrySet().stream().forEach(System.out::println);
	}
}
