package com.lec.spring.controller;

import com.lec.spring.domain.Coupon;
import com.lec.spring.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    // 생성
    @GetMapping("/create")
    public void create(){};

    // 생성 완료
    @PostMapping("/create")
    public String createOk(Coupon coupon, Model model){
        model.addAttribute("result", couponService.write(coupon));
        return "coupon/createOk";
    }

    // 목록
    @GetMapping("/list")
    public void list(Model model) { model.addAttribute("list", couponService.list());}

    // 하나 불러오기
    @GetMapping("/detail/{id}")
    public String detail(Long id, Model model){
        model.addAttribute("coupon", couponService.selectById(id));
        return "coupon/detail";
    }

    @GetMapping("/modify/{id}")
    public String modify(Long id, Model model){
        model.addAttribute("coupon", couponService.selectById(id));
        return "coupon/modify";
    }

    @PostMapping("/modify")
    public String modifyOk(Coupon coupon, Model model){
        model.addAttribute("result", couponService.update(coupon));
        return "coupon/modifyOk";
    }

    @GetMapping("/remove")
    public String removeOk(Long id, Model model){
        model.addAttribute("result", couponService.deleteByid(id));
        return "coupon/deleteOk";
    }

}
