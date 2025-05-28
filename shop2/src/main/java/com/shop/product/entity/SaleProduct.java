package com.shop.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class SaleProduct {

    /**
     * 세일 상품 식별자 (기본키)
     * 자동 생성됨 (AUTO_INCREMENT 등 DB 전략에 따름)
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 세일 적용 대상 상품
     * 여러 개의 세일 이력이 하나의 상품(Product)에 연결될 수 있음 (N:1 관계)
     */
    @ManyToOne
    private Product product;

    /**
     * 세일(할인) 적용 가격
     * 해당 세일 기간 동안 적용될 판매 가격
     */
    private int salePrice;

    /**
     * 세일 시작 일시
     * 이 일시부터 salePrice가 적용됨
     */
    private LocalDateTime saleStart;

    /**
     * 세일 종료 일시
     * 이 일시까지 salePrice가 적용됨
     * 보통 종료일을 넘어가면 세일 미적용 상태로 취급함
     */
    private LocalDateTime saleEnd;

    /**
     * 등록자 아이디 또는 이름
     * 해당 세일 이력을 최초로 등록한 사용자 정보
     */
    private String createdBy;

    /**
     * 등록 일시
     * 해당 세일 이력이 시스템에 최초로 등록된 시간
     */
    private LocalDateTime createdAt;

    /**
     * 최종 수정자 아이디 또는 이름
     * 해당 세일 이력을 수정한 마지막 사용자 정보
     */
    private String updatedBy;

    /**
     * 최종 수정 일시
     * 해당 세일 이력이 가장 마지막으로 수정된 시간
     */
    private LocalDateTime updatedAt;
}