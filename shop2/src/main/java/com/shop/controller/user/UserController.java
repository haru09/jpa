package com.shop.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.dto.user.UserSignUpDto;
import com.shop.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 생성자 주입 예제
     * - ExampleService exampleService;
     * - AnotherService anotherService
     * RequiredArgsConstructor 해서 private final 시 아래와 같은 생성자가 롬복에 의해 자동 추가.
     * public ExampleController(ExampleService exampleService, AnotherService
     * anotherService) {
     * this.exampleService = exampleService;
     * this.anotherService = anotherService;
     * }
     */

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserSignUpDto());
        return "register";
    }

    @PostMapping("/register")
    public String signUp(@ModelAttribute("user") UserSignUpDto dto, Model model) {
        try {
            userService.registerUser(dto);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 과정에서 오류가 발생했습니다.");
            return "register";
        }
    }
}