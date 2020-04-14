package bank.controller;

import bank.entity.Users;
import bank.model.UsersModel;
import exception.Trouble;

import java.sql.SQLException;
import java.util.UUID;

public class UsersController {
    private UsersModel UsersModel;

    public UsersController(UsersModel UsersModel) {
        this.UsersModel = UsersModel;
    }

    public boolean authorise(String login, String password) throws SQLException {
        return (getUsers(login).getPassword().equals(password));
    }

    public String createUser(String login, String password, String phone, String address) throws SQLException, Trouble {
        Users users = new Users(UUID.randomUUID(), login, password, phone, address);
        UsersModel.insertUser(users);
        return login;
    }

    Users getUserByPhone(String phone) throws SQLException, Trouble {
        Users users = UsersModel.getUserByPhone(phone);
        if (users == null) {
            throw new Trouble("No users found with this phone");
        }
        return users;
    }

    String getUserIdByLogin(String login) throws SQLException, Trouble {
        Users users = getUsers(login);
        if (users == null) {
            throw new Trouble("No users found with this ID");
        }
        return users.getId().toString();
    }

    public boolean existUserByLogin(String login) throws SQLException {
        return getUsers(login) != null;
    }

    private Users getUsers(String login) throws SQLException {
        return UsersModel.getUserByLogin(login);
    }
}
