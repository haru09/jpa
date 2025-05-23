package com.shop.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html 렌더링
    }

    // UserDetailService로 변경
    // @PostMapping("/login")
    // public String doLogin(
    // @RequestParam String username,
    // @RequestParam String password,
    // HttpSession session, Model model) {

    // Optional<User> userOpt = userRepository.findByEmail(username);
    // if (userOpt.isPresent()) {
    // User user = userOpt.get();
    // if (passwordEncoder.matches(password, user.getPassword())) {
    // session.setAttribute("user", user);
    // return "redirect:/";
    // }
    // }
    // model.addAttribute("error", "이메일 또는 비밀번호가 일치하지 않습니다.");
    // return "login";
    // }
}