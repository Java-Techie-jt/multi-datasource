package com.javatechie.service;

import com.javatechie.entity.employee.Employee;
import com.javatechie.repository.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee getEmployee(int id) {
        Optional<Employee> employee = repository.findById(id);
        return employee.orElseThrow(() -> new NoSuchElementException("Employee with ID " + id + " not found"));
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Employee existingEmployee = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee with ID " + id + " not found"));
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setDept(updatedEmployee.getDept());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setAge(updatedEmployee.getAge());
        return repository.save(existingEmployee);
    }

    public void deleteEmployee(int id) {
        repository.deleteById(id);
    }

}
