package com.k2tech.ehcacheprac.components;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.k2tech.ehcacheprac.data.dao.Employee;
import com.k2tech.ehcacheprac.data.dao.EmployeeRepository;

@Component
public class Loader {

    @Autowired
    EmployeeRepository empRepo;

    @PostConstruct
    public void loadEmployees(){
        empRepo.saveAll(getTempEmployees());
    }

    private List getTempEmployees(){
        Employee e1 = new Employee(1,"kyru","Meerut");
        Employee e2 = new Employee(2,"aashi","Noida");
        List<Employee> empList = new ArrayList<>();
        empList.add(e1);
        empList.add(e2);
        return empList;


    }

}
