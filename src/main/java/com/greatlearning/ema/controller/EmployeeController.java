package com.greatlearning.ema.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.ema.entity.EmployeeEntity;
import com.greatlearning.ema.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
    @Autowired
	EmployeeService empService;
    
	@GetMapping("/list")
public String listStudents(Model model) {
		
		List<EmployeeEntity> employees = empService.listAll();
		
		System.out.println("Employees->" + employees);
		
		model.addAttribute("Employees", employees);
		
		// view 
		return "list-employees";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		EmployeeEntity theEmp = new EmployeeEntity();

		theModel.addAttribute("Employee", theEmp);

		return "Employee-form";
	}
	
	
	@PostMapping("/save")
	public String saveEmployee(
		@RequestParam("id") int id,
		@RequestParam("f_Name") String firstName,
		@RequestParam("l_Name") String lastName,
		@RequestParam("email") String email) {

		System.out.println(id);
		EmployeeEntity theEmp = null;
		
		if(id!=0)
		{
			theEmp = empService.findById(id);
			
			theEmp.setF_name(firstName);
			theEmp.setL_name(lastName);
			theEmp.setEmail(email);			
		}
		else
		{
			theEmp = new EmployeeEntity(firstName, lastName, email);
		}
		
		
		empService.save(theEmp);


		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";

	}	

	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(
			@RequestParam("employeeId") int theId,
			Model theModel) {

		
		EmployeeEntity theEmp = empService.findById(theId);


		theModel.addAttribute("Employee", theEmp);

		return "Employee-form";			
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		empService.deleteById(theId);


		return "redirect:/employees/list";
	}	
	
	
	@GetMapping("/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {

			model.addObject("msg", "Hi " + user.getName()
			+ ", you do not have permission to access this page!");

		} else {

			model.addObject("msg",
			"You do not have permission to access this page!");
		}

		model.setViewName("403");

		return model;
	}	
}
