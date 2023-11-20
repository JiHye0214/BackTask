package com.lec.spring;

import com.lec.spring.domain.Coupon;
import com.lec.spring.repository.CouponRepository;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

@SpringBootTest // 스프링 context를 로딩하여 테스트에 사용
class PostRepositoryTest {

    // MyBatis가 생성한 구현체들이 담겨 있는 SqlSession 객체
    // 기본적으로 스프링에 빈 객체가 생성되어 있어서 주입 받을 수 있다
    @Autowired
    private SqlSession sqlSession;

    // @Test : 단독 시행 가능
    // DBeaver에 바로 적용됨 !!
    @Test
    void test1(){
        CouponRepository couponRepository = sqlSession.getMapper(CouponRepository.class);
        System.out.println("[최초]");

        couponRepository.findAll().forEach(e -> System.out.println(e));

        Coupon coupon = Coupon.builder()
                .name("bread")
                .kind("정기권")
                .sno("dfe4-9e-4s")
                .build();

        System.out.println("before : " + coupon);
        couponRepository.regist(coupon);
        System.out.println("after : " + coupon);
        System.out.println("=================================");

        couponRepository.findAll().forEach(e -> System.out.println(e));

        coupon.setName("vitamin C");
        coupon.setKind("정기권");
        couponRepository.modify(coupon);
        System.out.println("[수정후] " + coupon);

//        couponRepository.remove(coupon);
//        System.out.println("delete after------------------------");
        couponRepository.findAll().forEach(e -> System.out.println(e));

        System.out.println("테스트 완료");
    }
}