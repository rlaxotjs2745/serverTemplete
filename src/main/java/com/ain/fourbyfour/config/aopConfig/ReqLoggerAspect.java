package com.ain.fourbyfour.config.aopConfig;


import com.ain.fourbyfour.util.ConnectionUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class ReqLoggerAspect {

    private final ConnectionUtil connectionUtil;


    public ReqLoggerAspect(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Pointcut("@annotation(com.ain.fourbyfour.config.customAnnotation.ControllerReqLog)")
    private void controllerCut() {  }

    private LocalDateTime startTime;

    @Before("controllerCut()")
    public void controllerLoggerAdvice(JoinPoint joinPoint){
        startTime = LocalDateTime.now();
        Signature signature = joinPoint.getSignature();

        String typeName = signature.getDeclaringTypeName().toString();
        String methodName = signature.getName();

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        log.info(
                "\nHTTP REQUEST" +
                "\nClient IP : " + connectionUtil.getClientIP(request) +
                "\nRequest URL : " + request.getServletPath() +
                "\nHandle Controller : " + typeName +
                "\nMethod : " + methodName + "\n"
        );
    }

    @AfterReturning(pointcut = "controllerCut()", returning = "result")
    public void controllerLogAfter(JoinPoint joinPoint, Object result){
        long nowTimeStamp = Timestamp.valueOf(LocalDateTime.now()).getTime() - Timestamp.valueOf(startTime).getTime();

        log.info(
                "\nHTTP RESPONSE" +
                "\nController Method : " + joinPoint.getSignature().getName() +
                "\nExcution Time : " + nowTimeStamp + "ms" +
                "\nMethod Return Value : " + result + "\n"
        );
    }
}
