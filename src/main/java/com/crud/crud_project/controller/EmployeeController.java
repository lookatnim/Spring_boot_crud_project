package com.crud.crud_project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.crud.crud_project.dto.EmployeeDto;
import com.crud.crud_project.service.EmployeeService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
@CrossOrigin

public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        System.out.println("employeeDto||||||||||||||||||||||||||||| "+employeeDto.toString());
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long empid){
        System.out.println("id||||||||||||||||||||||||||||| "+empid);
        EmployeeDto employeeDto = employeeService.getEmployeeById(empid);
        return ResponseEntity.ok(employeeDto);
    }
    
    @GetMapping("getall")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employeeDto = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDto);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empid, @RequestBody EmployeeDto employeeDto){
        EmployeeDto updatedEmployee = employeeService.updateEmployee(empid, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }
    
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empid){
        employeeService.deleteEmployee(empid);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
