package com.nt.validator;
import org.springframework.stereotype.Component;



import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nt.entity.Employee;
@Component
public class EmployeeValidator implements Validator {

	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		System.out.println("EmployeeValidator.supports()");
		return Employee.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		System.out.println("EmployeeValidator.validate()");
		Employee emp=(Employee)target;
		//Float deptno=(float)emp.getDeptno();
		//System.out.println(emp.getDeptno().isNaN());
		if(emp.getEname()==null || emp.getEname().length()==0 || emp.getEname().equals(""))
		{
			errors.rejectValue("ename", "emp.ename.required");
		}
		else if(emp.getEname().length()<5) {
			errors.rejectValue("ename", "emp.ename.minlength");
		}
		if(emp.getJob()==null || emp.getJob().length()==0 || emp.getJob().equals(""))
		{
			errors.rejectValue("job", "emp.job.required");
		}
		else if(emp.getJob().length()<5) {
			errors.rejectValue("job", "emp.job.minlength");
		}

		
			if(emp.getSalary()==null) {
				errors.rejectValue("salary","emp.salary.required");
			}
			
			else if(emp.getSalary()<10000 || emp.getSalary()>1000000)
			{
				errors.rejectValue("salary", "emp.salary.range");
			}
		
		
		if(emp.getDeptno()==null) {
			errors.rejectValue("deptno","emp.deptno.required");
		}
		
		else if(emp.getDeptno()<10 || emp.getDeptno()>100)
		{
			errors.rejectValue("deptno", "emp.deptno.range");
		}
	}

}
