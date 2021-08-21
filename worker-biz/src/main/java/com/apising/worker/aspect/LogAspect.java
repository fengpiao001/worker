package com.apising.worker.aspect;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.apising.common.lang.session.SessionLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


/**
 *
 * @author
 */
@Aspect
@Slf4j
@EnableAspectJAutoProxy
@Component
public class LogAspect {

    Logger accessLog = LoggerFactory.getLogger("access_log");

    private final static String TRACE_ID = "traceId";

    /**
     * 切面采集模块的信息放入MDC中
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@within(org.springframework.web.bind.annotation.RestController) || "
            +"@within(org.springframework.stereotype.Controller) ")
    public Object executeRemoteAop(ProceedingJoinPoint joinPoint) throws Throwable{
        if(StringUtils.isEmpty(MDC.get(TRACE_ID))){
            MDC.put(TRACE_ID, UUID.randomUUID().toString().replaceAll("\\-",""));
        }
        StringBuilder sb = new StringBuilder();
        Object result = null;
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();
        sb.append(className).append("#").append(methodName).append("|req:")
                .append(JSONObject.toJSONString(filterParams(params))).append("|resp:");
        try {
            result = joinPoint.proceed();
            sb.append(JSONObject.toJSONString(result));
        }catch (Throwable e){
            sb.append(ExceptionUtils.getStackTrace(e));
            throw e;
        }finally {
            sb.append("|mobile=").append(SessionLocal.getMobile());
            accessLog.info("accessLog: {}",sb.toString());
        }
        return result;
    }


    /**
     * 过滤参数 HttpServletRequest,HttpServletResponse
     * @param paramArr
     * @return
     */
    private List<Object> filterParams(Object[] paramArr){
        if(paramArr == null){
            return Collections.emptyList();
        }
        List<Object> list = new ArrayList<>(paramArr.length);
        for(Object item : paramArr){
            if(item instanceof HttpServletRequest
            || item instanceof HttpServletResponse
            || item instanceof MultipartFile){
                continue;
            }
            list.add(item);
        }
        return list;
    }

}
