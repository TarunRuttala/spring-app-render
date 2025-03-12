package com.tarun.UserInfo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Username is required")
    @Column(nullable = false,unique = true)
    private String username;

    @NotBlank(message = "Password is required")
    @Column(nullable= false)
    private String password;


}
