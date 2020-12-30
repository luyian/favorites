package com.it.favorites.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@ResponseBody
public class ValidParamAdvice {
    /**
     * 定义切点，切点为com.it.controller包和子包里任意方法的执行
     */
    @Pointcut("execution(* com.it.favorites.controller.*..*(..))")
    public void controllerPointCut() {
    }

    @Around("controllerPointCut() && args(.., bindingResult)")
    public Object doAround(ProceedingJoinPoint joinPoint, BindingResult bindingResult) throws Throwable {
        // 收集并返回参数校验错误信息，这里根据实际返回结果类自己封装
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>(16);
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            return errors.toString();
        }

        return joinPoint.proceed(joinPoint.getArgs());
    }
}
