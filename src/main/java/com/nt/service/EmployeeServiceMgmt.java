package com.nt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nt.entity.Employee;
import com.nt.repositoy.IEmployee;
@Service
public class EmployeeServiceMgmt implements IEmployeeService {

	@Autowired
	private IEmployee empRepo;
	
	@Override
	public Iterable<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return empRepo.findAll(Sort.by("job").ascending());
	}
	@Override
	public String saveEmployee(Employee employee) {
		
		return "Employee saved with :: "+empRepo.save(employee).getEmpno();
	}
	@Override
	public Employee editEmployee(Integer empno) {
		// TODO Auto-generated method stub
		Optional<Employee> optional = empRepo.findById(empno);
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;
	}
	@Override
	public String saveEditedEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRepo.save(employee).getEmpno()+" Employee saved";
	}
	@Override
	public String deleteEmployeeById(Integer empno) {
		// TODO Auto-generated method stub
		empRepo.deleteById(empno);
		return "Employee with empno "+empno+" deleted";
	}

}
