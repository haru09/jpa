package com.shop.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // 세션 관리
                .sessionManagement(session -> session
                        //.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않음 (주로 JWT 토큰 기반 서비스)
                        // .sessionCreationPolicy(SessionCreationPolicy.NEVER) // 절대 세션 생성 안함, 단 요청에 세션이 있으면 사용
                        // .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // 요청마다 새로운 세션 생성
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 필요할 경우에만 세션 생성 (기본값)
                        .maximumSessions(1) // 한 계정으로 최대 1개 세션만 허용(중복 로그인 방지)
                        .maxSessionsPreventsLogin(false) // true : 이미 접속 중일 때 추가 로그인 차단, 기존 로그인 강제 만료 후 새 로그인 허용

                )
                // 인증 및 인가 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/css/**", "/js/**", "/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                // h2 console 확인하기 위해 Frame 옵션 예외 추가
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                // 로그인 설정
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        /*
                        * 1. **GET 로그아웃을 직접 허용할 경우 보안상 위험이 커집니다.**
                            - CSRF 등 악의적인 자동로그아웃 유발 가능
                            - 의도하지 않은 로그아웃 발생
                            - Spring Security의 **기본 로그아웃 요청 방식은 POST** 메소드,
                        * */
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))   // GET 메소드로 로그아웃 허용 (중복 로그인 차단),
                        .permitAll()
                )
                // 예외 처리
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            // 인증되지 않은 사용자가 접근할 때
                            response.sendRedirect("/login");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            // 인가(권한)가 없는 사용자가 접근할 때
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");
                        })
                );
        return http.build();
    }
}