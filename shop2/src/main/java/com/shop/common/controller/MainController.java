package com.shop.common.controller;

import com.shop.product.entity.Product;
import com.shop.product.entity.SaleProduct;
import com.shop.product.service.ProductService;
import com.shop.product.service.SaleProductService;
import com.shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserService userService;
    private final ProductService productService;        // 추가 필요
    private final SaleProductService saleProductService; // 추가 필요

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

            // 신규상품: 등록일 기준 최근 10개
            List<Product> newProducts = productService.findNewProducts();
            // 인기상품: 임시로 상품번호 내림차순 10개(실제 서비스에서는 별도 로직 필요)
            List<Product> popularProducts = productService.findPopularProducts();
            // 특가상품: SaleProduct의 기간 내 상품
            List<SaleProduct> saleProducts = saleProductService.findCurrentSaleProducts();

            model.addAttribute("newProducts", newProducts);
            model.addAttribute("popularProducts", popularProducts);
            model.addAttribute("saleProducts", saleProducts);
        }

        return "index"; // templates/index.html 렌더링
    }
}