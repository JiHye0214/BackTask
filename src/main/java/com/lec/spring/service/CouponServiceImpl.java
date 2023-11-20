package com.lec.spring.service;

import com.lec.spring.domain.Coupon;
import com.lec.spring.repository.CouponRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{

    // repository 주입 받기
    private CouponRepository couponRepository;
    @Autowired
    public CouponServiceImpl(SqlSession sqlSession){ // 생성자
        couponRepository = sqlSession.getMapper(CouponRepository.class);
        System.out.println("CouponService() is Created");
    }

    @Override
    public int write(Coupon coupon) {
        return couponRepository.regist(coupon);
    }

    @Override
    public List<Coupon> list() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon selectById(Long id) {
        return couponRepository.findById(id);
    }

    @Override
    public int update(Coupon coupon) {
        return couponRepository.modify(coupon);
    }

    @Override
    public int deleteById(Long id) {
        int result = 0;
        Coupon coupon = couponRepository.findById(id);
        if(coupon != null){
            result = couponRepository.remove(coupon);
        }
        return result;
    }
}
