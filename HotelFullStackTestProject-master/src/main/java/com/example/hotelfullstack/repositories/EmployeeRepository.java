package com.example.hotelfullstack.repositories;

import com.example.hotelfullstack.models.CarCategory;
import com.example.hotelfullstack.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFullNameContainingOrFullNameContaining(String name1, String name2);
    @Query("SELECT e FROM Employee e LEFT JOIN Position p ON e.position.id = p.id")
    List<Employee> findEmployeesWithPosition();
}

