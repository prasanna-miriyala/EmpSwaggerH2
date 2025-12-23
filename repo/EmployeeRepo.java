package com.example.employee.repo;
import com.example.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EmployeeRepo extends JpaRepository<Employee, String>{
    Optional<Employee> findByEmail(String email);
}