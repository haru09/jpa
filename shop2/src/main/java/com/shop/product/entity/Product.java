package com.shop.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

import lombok.*;

/**
 * 상품 정보를 관리하는 엔티티 클래스.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * 상품의 고유 식별자 (자동 생성).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 코드(중복 불가).
     * 내부 관리 및 외부 시스템과의 통신에서 사용됩니다.
     */
    private String productCode;

    /**
     * 상품명.
     * 사용자 화면 등에서 노출되는 대표 상품 이름입니다.
     */
    private String name;

    /**
     * 전시 여부.
     * true면 상품이 쇼핑몰 등 사용자 화면에 노출됩니다.
     */
    private boolean display;

    /**
     * 상품 가격(단위: 원).
     */
    private int price;

    /**
     * 1회(한 번)에 주문 가능한 최대 수량.
     * 장바구니/주문 시 한 번에 이 수량을 초과해서 주문 불가.
     */
    private int orderMaxPerOnce;

    /**
     * 한 사용자가 한 달간 주문 가능한 최대 수량.
     * 월 단위 제한에 사용됩니다.
     */
    private int orderMaxPerMonth;

    /**
     * 한 번 주문시 최소 주문해야 하는 수량.
     */
    private int orderMin;

    /**
     * 주문 단위(예: 2개 단위씩만 주문 가능할 때 2).
     */
    private int orderUnit;

    /**
     * 상품 등록자(사용자 또는 관리자 아이디 등).
     */
    private String createdBy;

    /**
     * 상품 등록일시.
     */
    private LocalDateTime createdAt;

    /**
     * 상품 정보 최종 수정자.
     */
    private String updatedBy;

    /**
     * 상품 정보 최종 수정일시.
     */
    private LocalDateTime updatedAt;

    /**
     * 상품 조회수.
     * 사용자들이 해당 상품 상세페이지 또는 목록에서 본 횟수의 합계입니다.
     */
    private int viewCount;
}