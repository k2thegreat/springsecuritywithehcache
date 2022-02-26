package com.k2tech.ehcacheprac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.k2tech.ehcacheprac.data.dao.Employee;
import com.k2tech.ehcacheprac.data.dao.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    public List<Employee> getEmployees(){
        return empRepo.findAll();
    }

    @Cacheable(value="employeesCache",key="#name")
    public Employee getEmployee(String name){
        return empRepo.findByName(name);
    }
}
