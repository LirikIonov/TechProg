package com.sgu.banksspring.controller.component;

import com.sgu.banksspring.entity.Users;
import com.sgu.banksspring.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUsersController implements UserDetailsService {
    private UsersModel usersModel;

    @Autowired
    public JwtUsersController(UsersModel usersModel) {
        this.usersModel = usersModel;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByLogin(username);
    }

    private UserDetails loadUserByLogin(String login) throws UsernameNotFoundException {
        Users user = usersModel.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with login: " + login);
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                new ArrayList<>());
    }
}
