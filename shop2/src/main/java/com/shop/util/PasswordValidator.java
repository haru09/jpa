package com.shop.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    public void validate(String password) {
        // 5자 이상, 대/소문자 포함, 특수문자 한 개 이상
//        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={}:;\"',.<>/?`~\\[\\]\\\\|]).{5,}$";
        String regex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\\-={}:;\"',.<>/?`~\\[\\]\\\\|]).{5,}$";
        if (password == null || !password.matches(regex)) {
            throw new IllegalArgumentException("비밀번호는 5자 이상, 영문 대/소문자, 특수문자 1개 이상을 포함해야 합니다.");
        }
    }
}