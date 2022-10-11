package com.nt.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.Employee;
import com.nt.service.IEmployeeService;
import com.nt.validator.EmployeeValidator;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService service;
	
	@Autowired
	private EmployeeValidator validator;
	
	@GetMapping("/")
	public String showHome()
	{
		return "home";
	}
	@GetMapping("/show_employee")
	public String showRegister(Map<String, Object> map)
	{
		Iterable<Employee> employees = service.getAllEmployees();
		map.put("emplist", employees);
		return "report";
	}
	
	@GetMapping("/insert_employee")
	public String showRegister(@ModelAttribute("employee") Employee emp)
	{
		return "register";
	}
	
	@PostMapping("/insert_employee")
	public String addEmployee(@ModelAttribute("employee") Employee emp,
								BindingResult errors,
								RedirectAttributes attributes
								)
	{
		if (validator.supports(emp.getClass())) {
			validator.validate(emp, errors);
			if (errors.hasErrors()) {
				System.out.println(errors);
				return "register";
			}
		}
		String message = service.saveEmployee(emp);
		//Iterable<Employee> employees = service.getAllEmployees();
		//map.put("message", employee);
		attributes.addFlashAttribute("message",message);
		//map.put("emplist", employees);
		return "redirect:show_employee";
	}
	
	@GetMapping("/edit_employee")
	public String getEditPage(@RequestParam("empno") Integer empno,
								@ModelAttribute("emp") Employee emp)
	{
		Employee employee = service.editEmployee(empno);
		BeanUtils.copyProperties(employee, emp);
		
		return "edit";
	}
	
	@PostMapping("/edit_employee")
	public String submitEditPage(@ModelAttribute("employee")Employee employee,
								RedirectAttributes attr)
	{
		String employee2 = service.saveEditedEmployee(employee);
		//Iterable<Employee> employees = service.getAllEmployees();
		//map.put("emplist", employees);
		/*map.put("message", employee2);*/
		attr.addFlashAttribute("message", employee2);
		return "redirect:show_employee";
	}
	@GetMapping("/delete_employee")
	public String deleteEmployee(@RequestParam("empno") Integer empno,
								Map<String,Object> map)
	{
		String employeeById = service.deleteEmployeeById(empno);
		//Iterable<Employee> employees = service.getAllEmployees();
		//map.put("emplist", employees);
		map.put("message", employeeById);
		return "redirect:show_employee";
	}
	
	
	

}
