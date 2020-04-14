package com.sgu.banksspring.model;

import com.sgu.banksspring.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountModel extends CrudRepository<Account, Long> {
    List<Account> findByClientId(Long clientId);
}
