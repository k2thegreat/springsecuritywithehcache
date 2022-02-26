package com.k2tech.ehcacheprac.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthGroupRepository extends JpaRepository<AuthGroup,Integer> {

    public List<AuthGroup> findAllByUserName(String userName);
}
