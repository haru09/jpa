package com.shop.repository;

import com.shop.user.entity.User;
import com.shop.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("User 저장 및 조회 테스트")
    void saveAndFindUser() {
        // given
        User user = new User();
        user.setName("홍길동");
        user.setBirth("1990-01-01");
        user.setEmail("hong@test.com");
        user.setPassword("pass1234");

        // when
        userRepository.save(user);

        // then
        Optional<User> found = userRepository.findByEmail("hong@test.com");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("홍길동");
        assertThat(found.get().getBirth()).isEqualTo("1990-01-01");
        assertThat(found.get().getEmail()).isEqualTo("hong@test.com");
    }

    @Test
    @DisplayName("이메일 존재 여부 테스트")
    void existsByEmailTest() {
        // given
        User user = new User();
        user.setName("이몽룡");
        user.setBirth("1985-05-05");
        user.setEmail("lee@test.com");
        user.setPassword("secret");

        userRepository.save(user);

        // when
        boolean exists = userRepository.existsByEmail("lee@test.com");

        // then
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("존재하지 않는 이메일 조회 테스트")
    void findByEmailNotExistTest() {
        // when
        Optional<User> result = userRepository.findByEmail("notexist@example.com");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("이메일 중복 저장 불가 테스트")
    void duplicateEmailTest() {
        // given
        User user1 = new User();
        user1.setName("첫번째");
        user1.setBirth("1992-01-01");
        user1.setEmail("duplicate@test.com");
        user1.setPassword("pw1");
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("두번째");
        user2.setBirth("1995-05-05");
        user2.setEmail("duplicate@test.com");
        user2.setPassword("pw2");

        // when & then
        org.junit.jupiter.api.Assertions.assertThrows(
                Exception.class,
                () -> userRepository.saveAndFlush(user2),
                "같은 이메일 저장 시 예외 발생"
        );
    }
}