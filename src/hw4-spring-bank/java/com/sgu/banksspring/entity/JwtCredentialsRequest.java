package com.sgu.banksspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtCredentialsRequest {
    private String username;
    private String password;
}
