package com.sgu.banksspring.configuration;

import com.sgu.banksspring.controller.component.AccountController;
import com.sgu.banksspring.controller.component.OperationController;
import com.sgu.banksspring.controller.component.UsersController;
import com.sgu.banksspring.model.AccountModel;
import com.sgu.banksspring.model.OperationModel;
import com.sgu.banksspring.model.UsersModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwesomeConfig {
    @Bean
    public AccountController accountController(AccountModel accountModel, UsersController usersController, OperationController operationController) {
        return new AccountController(accountModel, operationController, usersController);
    }

    @Bean
    public OperationController operationController(OperationModel operationModel) {
        return new OperationController(operationModel);
    }

    @Bean
    public UsersController usersController(UsersModel usersModel) {
        return new UsersController(usersModel);
    }
}
