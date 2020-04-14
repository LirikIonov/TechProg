package com.sgu.banksspring.controller.component;

import com.sgu.banksspring.entity.Users;
import com.sgu.banksspring.model.UsersModel;

import java.sql.SQLException;
import java.util.UUID;

public class UsersController {
    private UsersModel usersModel;

    public UsersController(UsersModel UsersModel) {
        this.usersModel = UsersModel;
    }

    private Users getUsers(String login) throws SQLException {
        return usersModel.findByLogin(login);
    }

    public void createUser(String login, String password, String phone, String address) {
        Users user = new Users(login, password, phone, address);
        usersModel.save(user);
    }

    public Long getUserIdByLogin(String login) {
        Users user = usersModel.findByLogin(login);
        return user.getId();
    }

    public Users getUserByPhone(String phone) {
        return usersModel.findByPhone(phone);
    }

    public Users getUserByLogin(String login) {
        return usersModel.findByLogin(login);
    }

    public boolean existsUserByPhone(String phone) {
        return usersModel.existsUserByPhone(phone);
    }

    public boolean existsUserByLogin(String username) {
        return usersModel.existsUserByLogin(username);
    }
}
