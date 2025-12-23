package com.example.employee.service;
import com.example.employee.entity.Employee;
import com.example.employee.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface EmployeeService{
                   Employee  saveEmployee(Employee employee);
                   Employee  getEmployee(String id);
                   List<Employee> getAllEmployees();
                   Employee  updateEmployee(String id,Employee employee);
                          void     deleteEmployee(String id);

}