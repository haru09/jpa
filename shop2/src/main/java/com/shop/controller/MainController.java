package com.shop.controller;

import com.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;

    /**
     * 메인 페이지 진입 시 사용자 이름과 로그인 시각을 모델에 담아 템플릿에 전달합니다.
     *
     * @param model        View에 데이터를 전달하기 위한 모델 객체
     * @param userDetails  Spring Security에서 인증된 사용자 정보
     * @return             렌더링할 템플릿 이름(templates/index.html)
     */
    @GetMapping({ "/", "/index", "/main" })
    public String index(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // 사용자가 로그인 했을 때만 처리 (비로그인 시 userDetails = null)
        if (userDetails != null) {
            // findByEmail Optional<User> 반환으로 ifPresent() 사용
            userService.findByEmail(userDetails.getUsername())
                    .ifPresent(user -> {           // user가 null이 아니면(값이 존재하면) 이 코드 실행
                        model.addAttribute("name", user.getName());
                    });

            // 로그인 현재 시각 추가
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String loginTime = java.time.LocalDateTime.now().format(formatter);
            model.addAttribute("loginTime", loginTime);
        }

        return "index"; // templates/index.html 렌더링
    }
}