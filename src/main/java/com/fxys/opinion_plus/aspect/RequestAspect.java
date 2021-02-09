package com.fxys.opinion_plus.aspect;


import com.alibaba.fastjson.JSON;

import com.auth0.jwt.JWT;
import com.fxys.opinion_plus.domain.Log;
import com.fxys.opinion_plus.enums.ResultCodeEnums;
import com.fxys.opinion_plus.exception.OpinionException;
import com.fxys.opinion_plus.service.ILogService;
import com.fxys.opinion_plus.util.StringUtils;
import com.fxys.opinion_plus.util.ThreadLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * 切面输出基本信息
 * 以及记录日志
 *
 * @author: zhouzhou
 * @date: 2020/03/24
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

    @Autowired
    private ILogService logService;

    /**
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     */
    @Pointcut("execution( * com.fxys.*.controller..*(..))")
    public void logPointCut() {
    }

    /**
     * 方法执行之前调用
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        // 记录下请求内容
        printRequestLog(joinPoint, request, uri);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();
        long time = System.currentTimeMillis() - startTime;
        log.info("耗时 : {}", time);
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setTime(time);
        logger.setMessage("操作成功");
        logService.save(logger);
        return ob;
    }

    /**
     * 后置通知
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        String result = JSON.toJSONString(ret);
        log.info("返回值：{}", result);
        Log logger = ThreadLocalContext.get().getLogger();
        log.info("length:"+result.length());
        if(result.length()<1000){
            logger.setResult(result);
        }else {
            logger.setResult(ret.getClass().toString());
        }

        logger.setCreateTime(new Date());


    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setStatus(ResultCodeEnums.REQUEST_ERROR.code());
        String exception = StringUtils.getPackageException(e, "com.fxys");
        logger.setMessage(exception);
        logger.setTime(0L);
        logger.setCreateTime(new Date());
        String result = StringUtils.getPackageException(e, "OpinionException");
        logger.setResult(result);
        logService.save(logger);


    }

    /**
     * 打印请求日志
     *
     * @param joinPoint
     * @param request
     * @param uri
     */
    private void printRequestLog(JoinPoint joinPoint, HttpServletRequest request, String uri) {
        // 拿到切面方法

        log.info("请求地址 : {}", uri);
        log.info("请求方式 : {}", request.getMethod());
        // 获取真实的ip地址
        String ip = StringUtils.getRemoteIp(request);
        log.info("IP : {}", ip);
        String controllerName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("方法 : {}.{}", controllerName, joinPoint.getSignature().getName());
        String params = Arrays.toString(joinPoint.getArgs());

        log.info("请求参数：{}", params);
        // 获取token
        String token = request.getHeader("Authentication");

        // 获取日志实体
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setUrl(uri);
        if(params.length()<500){
            logger.setParams(params);
        }else {
            logger.setParams(joinPoint.getArgs().getClass().toString());
        }

        logger.setStatus(ResultCodeEnums.REQUEST_SUCCESS.code());
        logger.setMethod(request.getMethod());
        logger.setIp(ip);
        Long id;
        try{
            if(token!=null&&token!=""){
                id= JWT.decode(token).getClaim("id").asLong();
                logger.setUid(id);
            }


        }catch (Exception e){

            throw new OpinionException(ResultCodeEnums.SYSTEM_ERROR);
        }


    }

}