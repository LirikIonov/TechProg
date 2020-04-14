package com.sgu.banksspring.controller;

import com.sgu.banksspring.controller.component.JwtUsersController;
import com.sgu.banksspring.controller.component.UsersController;
import com.sgu.banksspring.entity.JwtCredentialsRequest;
import com.sgu.banksspring.entity.Users;
import com.sgu.banksspring.jwt.JwtToken;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/users")
public class MetaUsersController {
    private UsersController usersController;
    private AuthenticationManager authManager;
    private JwtUsersController jwtUsersController;
    private JwtToken jwtToken;

    @Autowired
    public MetaUsersController(UsersController userService, AuthenticationManager authManager,
                               JwtUsersController jwtUsersController, JwtToken jwtToken) {
        this.usersController = userService;
        this.authManager = authManager;
        this.jwtUsersController = jwtUsersController;
        this.jwtToken = jwtToken;
    }

    @PostMapping("/signup")
    public Boolean create(@RequestBody Users body) throws ValidationException {
        if (usersController.existsUserByLogin(body.getLogin())) {
            throw new ValidationException("username already existed");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(body.getPassword());
        usersController.createUser(body.getLogin(), encodedPassword, body.getPhone(), body.getAddress());
        return true;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtCredentialsRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUsersController
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok("Bearer " + token);
    }

    @GetMapping("/info/{phone}")
    @ApiOperation("get user")
    public Users getUser(@PathVariable("phone") String phone) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        return usersController.getUserByPhone(phone);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

