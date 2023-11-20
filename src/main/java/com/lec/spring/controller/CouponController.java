package com.lec.spring.controller;

import com.lec.spring.domain.Coupon;
import com.lec.spring.domain.CouponValidator;
import com.lec.spring.service.CouponService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Field;
import java.util.List;

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
    public String createOk(
            @Valid Coupon coupon,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("name", coupon.getName());
            redirectAttributes.addFlashAttribute("kind", coupon.getKind());
            redirectAttributes.addFlashAttribute("sno", coupon.getSno());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttributes.addFlashAttribute("error_" + err.getField(), err.getCode());
            }
            return "redirect:/coupon/create";
        }

        model.addAttribute("result", couponService.write(coupon));
        return "coupon/createOk";
    }

    // 목록
    @GetMapping("/list")
    public void list(Model model) { model.addAttribute("list", couponService.list());}

    // 하나 불러오기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        model.addAttribute("coupon", couponService.selectById(id));
        return "coupon/detail";
    }

    // {id} 쓰는 애들은 무조건 @PathVariable 달아야 됨 !!
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable Long id, Model model){
        model.addAttribute("coupon", couponService.selectById(id));
        return "coupon/modify";
    }

    @PostMapping("/modify")
    public String modifyOk(
            @Valid Coupon coupon,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("name", coupon.getName());
            redirectAttributes.addFlashAttribute("kind", coupon.getKind());
            redirectAttributes.addFlashAttribute("sno", coupon.getSno());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttributes.addFlashAttribute("error_" + err.getField(), err.getCode());
            }
            return "redirect:/coupon/modify/" + coupon.getId();
        }

        model.addAttribute("result", couponService.update(coupon));
        return "coupon/modifyOk";
    }

    @PostMapping("/remove")
    public String removeOk(Long id, Model model){
        model.addAttribute("result", couponService.deleteById(id));
        return "coupon/removeOk";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        System.out.println("initBinder is called");
        binder.setValidator(new CouponValidator());
    }

}
