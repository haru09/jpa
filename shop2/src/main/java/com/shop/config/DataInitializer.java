package com.shop.config;

import com.shop.entity.User;
import com.shop.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String email = "yseom@picoinnov.com";
        if (userRepository.findByEmail(email).isEmpty()) {
            User user = new User();
            user.setName("엄윤섭");
            user.setBirth("1982-12-21");
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("xptmxm1!"));
            userRepository.save(user);
            log.info("기본 유저 등록 완료: {}", email);
        }
    }
}