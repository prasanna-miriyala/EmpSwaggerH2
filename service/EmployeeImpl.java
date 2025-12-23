package com.example.employee.service;

import com.example.employee.controller.BadRequestException;
import com.example.employee.controller.ResourceNotFoundException;
import com.example.employee.entity.Employee;
import com.example.employee.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImpl  implements EmployeeService{
    @Autowired
    EmployeeRepo employeeRepo;
    public Employee saveEmployee(Employee employee){
        if(employee.getDepartment()==null||employee.getDepartment().isEmpty()){
            throw new BadRequestException("Department is required");
        }
        return employeeRepo.save(employee);
    }
   public Employee getEmployee(String id) {
       Employee optionalEmployee = employeeRepo.findById(id).
               orElseThrow(() -> new ResourceNotFoundException("Employee not found with id" + id));
       return optionalEmployee;
   }
    public List<Employee> getAllEmployees(){
         return employeeRepo.findAll();

    }

    public Employee  updateEmployee(String id, Employee employee){
           Employee exiistingEmp= employeeRepo.findById(id).
                   orElseThrow(()->new ResourceNotFoundException("Employee not found with id"+ id));
                   exiistingEmp.setFirstName(employee.getFirstName());
                   exiistingEmp.setLastName(employee.getLastName());
                   exiistingEmp.setDepartment(employee.getDepartment());
                   exiistingEmp.setSalary(employee.getSalary());
                   return employeeRepo.save(exiistingEmp);
    }
          public void  deleteEmployee(String id){
              Employee delEmp= employeeRepo.findById(id).
                      orElseThrow(()->new ResourceNotFoundException("Employee not found with id"+ id));
              employeeRepo.delete(delEmp);
    }

       }



