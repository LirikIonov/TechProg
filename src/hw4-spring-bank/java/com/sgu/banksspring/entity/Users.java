package com.sgu.banksspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String phone;
    @Column
    private String address;

    public Users(String login, String password, String phone, String address) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
}
