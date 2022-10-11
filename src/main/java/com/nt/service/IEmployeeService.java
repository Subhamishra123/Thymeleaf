package com.nt.service;

import com.nt.entity.Employee;

public interface IEmployeeService {
	
	public Iterable<Employee> getAllEmployees();
	public String saveEmployee(Employee employee);
	public Employee editEmployee(Integer empno);
	public String saveEditedEmployee(Employee employee);
	public String deleteEmployeeById(Integer empno);

}
