package com.shop.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpDto {
    private String name;
    private String birth;
    private String email;
    private String password;
    private String passwordCheck;
}
