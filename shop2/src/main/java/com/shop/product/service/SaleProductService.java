package com.shop.product.service;

import com.shop.product.entity.SaleProduct;
import com.shop.product.repository.SaleProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleProductService {

    private final SaleProductRepository saleProductRepository;

    /**
     * 현재 진행 중인 특가상품 조회
     */
    public List<SaleProduct> findCurrentSaleProducts() {
        LocalDateTime now = LocalDateTime.now();
        return saleProductRepository.findBySaleStartBeforeAndSaleEndAfter(now, now);
    }
}