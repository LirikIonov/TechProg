package com.sgu.banksspring.controller;

import com.sgu.banksspring.controller.component.OperationController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class MetaOperationController {
    private OperationController operationController;

    @Autowired
    public MetaOperationController(OperationController operationController) {
        this.operationController = operationController;
    }

    @GetMapping("/history/{id}")
    @ApiOperation("getting history of chosen account")
    public List<String> showHistory(@PathVariable("id") String id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        return operationController.getHistory(Long.valueOf(id));
    }
}