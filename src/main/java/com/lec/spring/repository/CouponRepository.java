package com.lec.spring.repository;

import com.lec.spring.domain.Coupon;

import java.util.List;

public interface CouponRepository {
    // DML은 숫자를 리턴하니까 int임
    // 쿠폰 등록
    int regist(Coupon coupon);

    // 전체 목록
    List<Coupon> findAll();

    // 하나 보기
    Coupon findById(Long id);

    // 수정
    int modify(Coupon coupon);

    // 삭제
    int remove(Coupon coupon);
}
