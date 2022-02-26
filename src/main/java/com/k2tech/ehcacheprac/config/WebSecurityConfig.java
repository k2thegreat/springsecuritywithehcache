package com.k2tech.ehcacheprac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.k2tech.ehcacheprac.service.ApplicationUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  ApplicationUserService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        .antMatchers("/","/k2tech/employees/all")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
  }


  @Bean
  public DaoAuthenticationProvider getAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    provider.setAuthoritiesMapper(getAuthoritiesMapper());
    return provider;
  }

  @Bean
  public GrantedAuthoritiesMapper getAuthoritiesMapper(){
    SimpleAuthorityMapper mapper = new SimpleAuthorityMapper() ;
//    mapper.setDefaultAuthority("USER");
    mapper.setConvertToUpperCase(true);
    return mapper;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(getAuthenticationProvider());
  }
}
