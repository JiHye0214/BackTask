package com.lec.spring.service;

import com.lec.spring.domain.Coupon;

import java.util.List;

public interface CouponService {
    int write(Coupon coupon); // 작성

    List<Coupon> list(); // 전체 목록
    Coupon selectById(Long id); // 하나 보기

    int update(Coupon coupon); // 수정

    int deleteById(Long id); // 삭제
}
