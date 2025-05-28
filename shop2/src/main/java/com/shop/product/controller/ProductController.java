package com.shop.product.controller;

import com.shop.product.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 상품 컨트롤러
 * - 상품 리스트 페이지 조회 및 검색 기능 제공
 * - 클라이언트의 상품 관련 웹 요청을 처리함
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    /**
     * 상품 관련 비즈니스 로직을 처리하는 서비스 클래스
     * - 상품 조회, 검색 등의 기능을 위임함
     */
    private final ProductService productService;

    /**
     * 생성자 주입 방식으로 ProductService 객체를 주입받음
     * @param productService 상품 서비스 객체
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 목록 페이지 요청 처리
     * - /products GET 요청에 매핑됨
     * - 검색 키워드가 있으면 이름으로 검색, 없으면 전체 목록 조회
     *
     * @param keyword  (옵션) 상품명 검색 키워드
     * @param pageable 페이징 정보 (page, size, sort 등)
     * @param model    뷰에 전달할 데이터를 저장하는 객체
     * @return         상품 리스트 뷰 이름 (product/list)
     */
    @GetMapping
    public String listProducts(@RequestParam(required = false) String keyword, Pageable pageable, Model model) {
        // 검색 키워드가 없거나 공백이면 전체 상품 목록 조회
        if (keyword == null || keyword.isBlank()) {
            model.addAttribute("products", productService.getAllProducts(pageable));
        } else {
            // 검색 키워드가 있으면 해당 키워드로 상품 이름을 검색하여 결과 조회
            model.addAttribute("products", productService.searchProductsByName(keyword, pageable));
        }
        // 검색 입력값을 model에 저장(뷰에서 검색창 초기값 등으로 활용)
        model.addAttribute("keyword", keyword);
        return "product/list";
    }
}