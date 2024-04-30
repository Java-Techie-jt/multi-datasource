package com.javatechie.repository.department;

import com.javatechie.entity.department.Department;
import com.javatechie.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}
