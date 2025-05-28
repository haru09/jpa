package com.shop.product.repository;

import com.shop.product.entity.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {
    // 현재 특가 진행 중인 상품 조회
    List<SaleProduct> findBySaleStartBeforeAndSaleEndAfter(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
