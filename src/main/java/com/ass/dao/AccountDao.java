package com.ass.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ass.model.Account;

public interface AccountDao extends JpaRepository<Account, String>{

}
