package com.crud.crud_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.crud_project.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee ,Long>{

}
