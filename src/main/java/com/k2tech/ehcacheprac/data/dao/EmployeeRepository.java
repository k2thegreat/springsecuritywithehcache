package com.k2tech.ehcacheprac.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Override
    List<Employee> findAll();

    Employee findByName(String name);
}
