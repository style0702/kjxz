package cn.kjxz.coupon.controller.validator;


import cn.kjxz.global.exception.GlobalException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class BindingResultUtil {

    public static void checkBindingResult(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            //获得所有参数校验的错误
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            StringBuilder stringBuilder = new StringBuilder("");

            for (ObjectError allError : allErrors) {
                FieldError fieldError = (FieldError) allError;
                stringBuilder.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append(";");
            }

            throw new GlobalException(stringBuilder.toString());
        }
    }
}
