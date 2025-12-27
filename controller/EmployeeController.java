
package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/saveData")
    public ResponseEntity<Employee> createEmp(@RequestBody @Valid Employee employee) {

        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmp() {
        System.out.println("controller  get method");
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable("id") String id){
        System.out.println("controller  get methods by idf");
       Employee byId= employeeService.getEmployee(id);
        return ResponseEntity.ok(byId);
    }

    @PutMapping
    public ResponseEntity<Employee> update(Employee employee,String id){
        Employee updateEmployee=employeeService.updateEmployee(id,employee);
        return  ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/employees/{id}")
   public ResponseEntity<Void> deleteEmployee(@PathVariable("id") String id) {
    employeeService.deleteEmployee(id);
    return ResponseEntity.noContent().build();
}


}
