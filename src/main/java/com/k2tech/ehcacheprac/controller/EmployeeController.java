package com.k2tech.ehcacheprac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k2tech.ehcacheprac.data.dao.Employee;
import com.k2tech.ehcacheprac.service.EmployeeService;

@RestController
@RequestMapping("/k2tech/employees")
public class EmployeeController {

  @Autowired EmployeeService empService;

  @GetMapping("/all")
  public List<Employee> getEmployees() {
    return empService.getEmployees();
  }

  @GetMapping("/{name}")
//  @PreAuthorize("hasRole('ROLE_USER')")
  public Employee getEmployee(@PathVariable String name) {
    return empService.getEmployee(name);
  }

  @GetMapping("/{name}/city")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public String getEmployeeCity(@PathVariable String name) {
    return empService.getEmployee(name).getAddress();
  }
}
