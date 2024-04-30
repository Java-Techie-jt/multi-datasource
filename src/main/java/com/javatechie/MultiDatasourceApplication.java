package com.javatechie;

import com.javatechie.entity.department.Department;
import com.javatechie.entity.employee.Employee;
import com.javatechie.repository.department.DepartmentRepository;
import com.javatechie.repository.employee.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MultiDatasourceApplication {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @PostConstruct
    public void initDBRecords() {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John Doe", "HR", 50000.0, "john@example.com", 30));
        employees.add(new Employee("Jane Smith", "Finance", 60000.0, "jane@example.com", 35));
        employeeRepository.saveAll(employees);

        List<Department> departments = new ArrayList<>();
        departments.add(new Department("Finance", "John Doe"));
        departments.add(new Department("Human Resources", "Jane Smith"));
        departments.add(new Department("Marketing", "Alice Johnson"));
        departments.add(new Department("Engineering", "Bob Williams"));
        departments.add(new Department("Sales", "Eva Brown"));
        departmentRepository.saveAll(departments);

    }



    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceApplication.class, args);
    }

}
