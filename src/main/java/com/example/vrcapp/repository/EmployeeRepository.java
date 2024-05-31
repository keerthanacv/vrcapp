package com.example.vrcapp.repository;

import com.example.vrcapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Add custom methods if needed

    @Query("SELECT e FROM Employee e WHERE e.department = ?1")
    List<Employee> findByDepartment(String department);
}