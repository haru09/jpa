package com.shop.product.service;

import com.shop.product.entity.Product;
import com.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 전체 상품 목록 페이징 조회
     */
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * 상품 ID로 단건 조회
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * 상품명 키워드로 페이징 검색
     */
    public Page<Product> searchProductsByName(String keyword, Pageable pageable) {
        return productRepository.findByNameContaining(keyword, pageable);
    }

    /**
     * 상품 등록
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * 상품 삭제
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * 등록일 기준 최근 10개 상품 조회
     */
    public List<Product> findNewProducts() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        return productRepository.findAll(pageable).getContent();
    }

    /**
     * 인기상품(임시로 상품번호 내림차순 상위 10개) 조회
     * 실제 인기 상품 선정 기준이 있다면 별도 컬럼이나 로직을 추가해야 함
     */
    public List<Product> findPopularProducts() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        return productRepository.findAll(pageable).getContent();
    }
}