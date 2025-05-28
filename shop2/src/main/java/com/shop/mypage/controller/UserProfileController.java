package com.shop.mypage.controller;

import com.shop.user.entity.User;
import com.shop.user.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userProfileService.getProfile(userDetails.getUsername());
        model.addAttribute("user", user);
        return "mypage/UserProfilePage";
    }
}