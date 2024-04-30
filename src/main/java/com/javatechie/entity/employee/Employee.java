package com.javatechie.entity.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String dept;
    private double salary;
    private String email;
    private int age;

    public Employee(String name, String dept, double salary, String email, int age) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.email = email;
        this.age = age;
    }
}
