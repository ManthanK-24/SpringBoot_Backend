package com.manthan.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manthan.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	// @Repository annotation is not require as SimpleJpaRepostiory have that already.
}
