package com.k2tech.ehcacheprac.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.k2tech.ehcacheprac.components.CustomBeanProcessor;
import com.k2tech.ehcacheprac.data.dao.AuthGroup;
import com.k2tech.ehcacheprac.data.dao.AuthGroupRepository;
import com.k2tech.ehcacheprac.data.dao.Employee;
import com.k2tech.ehcacheprac.data.dao.EmployeeRepository;
import com.k2tech.ehcacheprac.data.dao.User;
import com.k2tech.ehcacheprac.data.dao.UserRepository;

@Configuration
@EnableCaching
public class AppConfiguration {

  @Autowired EmployeeRepository empRepo;
  @Autowired UserRepository userRepo;
  @Autowired
  AuthGroupRepository authGroupRepo;

  @PostConstruct
  public void loadEmployees() {
    System.out.println("Post Construct Method call!");
    empRepo.saveAll(getTempEmployees());
    userRepo.saveAll((getUsers()));
    authGroupRepo.saveAll(getAuthGroups());
  }

  private List getTempEmployees() {
    Employee e1 = new Employee(3, "Ketan", "Noida");
    Employee e2 = new Employee(4, "Sanchit", "Meerut");
    List<Employee> empList = new ArrayList();
    empList.add(e1);
    empList.add(e2);
    return empList;
  }

  private List<User> getUsers() {
    User user1 = new User();
    user1.setId(1);
    user1.setName("Ketan");
    user1.setPassword("mypass");

    User user2 = new User();
    user2.setId(2);
    user2.setName("Aashi");
    user2.setPassword("mypass");
    List<User> users = new ArrayList<>();
    users.add(user1);
    users.add(user2);

    return users;
  }

  private List<AuthGroup> getAuthGroups() {
    AuthGroup auth1 = new AuthGroup();
    auth1.setId(1);
    auth1.setUserName("Ketan");
    auth1.setAuthGroup("USER");

    AuthGroup auth2 = new AuthGroup();
    auth2.setId(2);
    auth2.setUserName("Aashi");
    auth2.setAuthGroup("USER");

    AuthGroup auth3 = new AuthGroup();
    auth3.setId(4);
    auth3.setUserName("Aashi");
    auth3.setAuthGroup("ADMIN");

    List<AuthGroup> authGroups = new ArrayList<>();
    authGroups.add(auth1);
    authGroups.add(auth2);
    authGroups.add(auth3);
    return authGroups;
  }

  @Bean
  public CacheManager getCacheManager() {
    return new EhCacheCacheManager(getCacheManagerFactory().getObject());
  }

  @Bean
  public EhCacheManagerFactoryBean getCacheManagerFactory() {
    EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
    factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
    factoryBean.setShared(true);
    return factoryBean;
  }

  @Bean
  public CustomBeanProcessor initCustomBeanProcessor() {
    return new CustomBeanProcessor();
  }
}
