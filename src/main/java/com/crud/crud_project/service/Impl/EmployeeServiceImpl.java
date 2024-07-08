package com.crud.crud_project.service.Impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.crud.crud_project.dto.EmployeeDto;
import com.crud.crud_project.entity.Employee;
import com.crud.crud_project.mapper.EmployeeMapper;
import com.crud.crud_project.repository.EmployeeRepository;
import com.crud.crud_project.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee emp = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(emp);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((empliyee)-> EmployeeMapper.mapToEmployeeDto(empliyee))
        .collect(Collectors.toList()); 
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee emp = employeeRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Employee not found")
        );
        emp.setFirstName(employeeDto.getFirstName());
        emp.setLastName(employeeDto.getLastName());
        emp.setEmail(employeeDto.getEmail());
        Employee updatedEmployee = employeeRepository.save(emp);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Employee not found")
        );

        employeeRepository.deleteById(id);
        
    }

    
}
