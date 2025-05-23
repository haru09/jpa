package com.shop.service;

import com.shop.dto.user.UserSignUpDto;
import com.shop.entity.User;
import com.shop.repository.UserRepository;
import com.shop.util.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;
    private final BCryptPasswordEncoder passwordEncoder;

    public void registerUser(UserSignUpDto dto) {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }

        // 비밀번호 유효성 검사
        passwordValidator.validate(dto.getPassword());

        // 비밀번호 일치 확인
        if (!dto.getPassword().equals(dto.getPasswordCheck())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 저장
        User user = new User();
        user.setName(dto.getName());
        user.setBirth(dto.getBirth());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
