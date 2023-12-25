package com.manthan.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manthan.springboot.service.EmployeeService;
import com.manthan.springboot.model.Employee;

@RestController // internally has both controller & responsebody annotations.
@RequestMapping("/api/employees")
public class EmployeeController 
{
  
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) 
	{
		super();
		this.employeeService = employeeService;
	}
	
	// Method to create(Post Method) Employee Rest API
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee> (employeeService.saveEmployee(employee),HttpStatus.CREATED);
	}
	
	// Method to  get all employees Rest API
	@GetMapping
	 public List<Employee> getAllEmployees()
	 {
		 return employeeService.getAllEmployees();
	 }
	
	//Method to get employee by Id
	@GetMapping("{urlid}") // from client we need id, basically a url template variable 
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("urlid") long employeeId)
	{
		return new ResponseEntity<Employee> (employeeService.getEmployeeById(employeeId),HttpStatus.OK);
	}
	
	// Method to update employee by id REST API
	@PutMapping("{urlid}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("urlid") long id,
			                    @RequestBody Employee employee) // @Request Body for JSON to Java Object
	{
		return new ResponseEntity<Employee> (employeeService.updateEmployee(employee, id),HttpStatus.OK);
	}
	
	// Method to Delete employee REST API
	@DeleteMapping("{urlid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("urlid") long id)
	{
		//delete service call
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String> ("Employee Deleted Succesfully!",HttpStatus.OK);
	}
}
