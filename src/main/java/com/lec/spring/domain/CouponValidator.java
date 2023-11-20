package com.lec.spring.domain;

import org.hibernate.validator.constraintvalidators.RegexpURLValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class CouponValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports (" + clazz.getName() + ") is called");
        boolean result = Coupon.class.isAssignableFrom(clazz);
        System.out.println(result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Coupon coupon = (Coupon) target;
        System.out.println("validate() is called : " + coupon);

        // 공백 제한
        String name = coupon.getName();
        if(name == null || name.trim().isEmpty()){
            errors.rejectValue("name", "쿠폰 이름은 필수입니다");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kind", "쿠폰 종류는 필수입니다");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sno", "쿠폰 번호는 필수입니다");

        // 쿠폰 이름 글자수 15글자 제한
        if(name.trim().length() > 15){
            errors.rejectValue("name", "쿠폰 이름은 15자리까지 입력할 수 있습니다");
        }

        // 쿠폰 번호 양식 제한
        String regexSno = "^[a-z0-9]{4}-[a-z0-9]{2}-[a-z0-9]{2}$";
        String number = coupon.getSno();

        if(!Pattern.matches(regexSno, number)){
            errors.rejectValue("sno", "올바른 양식이 아닙니다.");
        }

    }
}
