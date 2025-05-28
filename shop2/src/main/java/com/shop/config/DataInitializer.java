package com.shop.config;

import com.shop.user.entity.User;
import com.shop.product.entity.Product;
import com.shop.user.repository.UserRepository;
import com.shop.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        registerDefaultUser();
        registerDummyProducts();
    }

    /**
     * 기본 유저 등록
     */
    private void registerDefaultUser() {
        String email = "yseom@picoinnov.com";
        if (userRepository.findByEmail(email).isEmpty()) {
            User user = new User();
            user.setName("엄윤섭");
            user.setBirth("1982-12-21"); // 타입에 따라 변환 필요
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("xptmxm1!"));
            // user.setRole("ROLE_USER"); // 필요 시 추가
            userRepository.save(user);
            log.info("기본 유저 등록 완료: {}", email);
        }
    }

    /**
     * 더미 상품 100개 등록
     */
    private void registerDummyProducts() {
        if (productRepository.count() > 0) {
            log.info("이미 상품 데이터가 존재합니다.");
            return;
        }
        for (int i = 1; i <= 100; i++) {
            Product product = Product.builder()
                    .productCode("P" + String.format("%04d", i))
                    .name("상품" + i)
                    .display(true)
                    .price(1000 * i)
                    .orderMaxPerOnce(10)
                    .orderMaxPerMonth(100)
                    .orderMin(1)
                    .orderUnit(1)
                    .createdBy("yseom@picoinnov.com")
                    .build();
            productRepository.save(product);
        }
        log.info("더미 상품 100개 등록 완료.");
    }

}