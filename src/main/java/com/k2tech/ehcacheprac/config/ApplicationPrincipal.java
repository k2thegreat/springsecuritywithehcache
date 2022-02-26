package com.k2tech.ehcacheprac.config;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.k2tech.ehcacheprac.data.dao.AuthGroup;
import com.k2tech.ehcacheprac.data.dao.User;

public class ApplicationPrincipal implements UserDetails {

  private User user;
  private List<AuthGroup> authGroups;

  public ApplicationPrincipal(User user, List<AuthGroup> authGroups) {
    this.user = user;
    this.authGroups = authGroups;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (authGroups == null) return Collections.emptySet();

    Set<GrantedAuthority> grantedAuths = new HashSet<>();
    this.authGroups.forEach(
        group -> {
          grantedAuths.add(new SimpleGrantedAuthority(group.getAuthGroup()));
        });
    return grantedAuths;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
