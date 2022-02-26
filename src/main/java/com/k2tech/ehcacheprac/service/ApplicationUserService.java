package com.k2tech.ehcacheprac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.k2tech.ehcacheprac.config.ApplicationPrincipal;
import com.k2tech.ehcacheprac.data.dao.AuthGroupRepository;
import com.k2tech.ehcacheprac.data.dao.User;
import com.k2tech.ehcacheprac.data.dao.UserRepository;

@Service
public class ApplicationUserService implements UserDetailsService {

  @Autowired private UserRepository userRepository;
  @Autowired private AuthGroupRepository authGroupRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByName(username);
    if (user == null) throw new UsernameNotFoundException("User not found -" + username);

    return new ApplicationPrincipal(user, authGroupRepository.findAllByUserName(username));
  }
}
