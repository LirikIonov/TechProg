package com.sgu.banksspring.model;

import com.sgu.banksspring.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersModel extends CrudRepository<Users, Long> {
    Users findByLogin(String login);
    Users findByPhone(String phone);
    boolean existsUserByLogin(String login);
    boolean existsUserByPhone(String phone);
}
