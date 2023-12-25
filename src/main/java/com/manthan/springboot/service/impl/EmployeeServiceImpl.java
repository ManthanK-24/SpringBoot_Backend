package com.manthan.springboot.service.impl;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.manthan.springboot.exception.ResourceNotFoundException;
import com.manthan.springboot.model.Employee;
import com.manthan.springboot.repository.EmployeeRepository;
import com.manthan.springboot.service.EmployeeService;


@Service
// @Transaction also not required, because JPA internally provides transaction to it's methods.
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	// No need of AutoWired Dependency because only single constructor present
	// So spring determines this is required spring bean
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent())
//		{
//			return employee.get(); // get method returns content of optional object
//		}
//		else
//		{
//			throw new ResourceNotFoundException("Employee","Id",id);
//		}
		
		// Lambda Expression way
		return employeeRepository.findById(id).orElseThrow(()->
					new ResourceNotFoundException("Employee","Id",id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		// check employee exists in db
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee","Id",id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		
		// save existing employee to DB 
		employeeRepository.save(existingEmployee);
		return existingEmployee;
		
	}

	@Override
	public void deleteEmployee(long id) {
		
		//check if employee exists in DB
		employeeRepository.findById(id).orElseThrow(()->
							new ResourceNotFoundException("Employee","Id",id));
		
		employeeRepository.deleteById(id);
		
	}

}
