package com.fxys.opinion_plus.handler;


import com.fxys.opinion_plus.resp.ErrorResult;
import com.fxys.opinion_plus.resp.GlobalResp;
import com.fxys.opinion_plus.util.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author :suyuzhou
 * @date :20210118
 * @description 参数返回处理器
 * @email :jack.su@jodoinc.com
 */
@ControllerAdvice(basePackages = {"com.fxys.opinion_plus.controller","com.fxys.opinion_plus.handler"})
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 是否支持advice功能
     * true=支持，false=不支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 处理response的具体业务方法
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ErrorResult) {
            ErrorResult errorResult = (ErrorResult) o;
            return GlobalResp.fail(errorResult.getStatus(),errorResult.getMessage());
        } else if (o instanceof String) {
            return JsonUtil.object2Json(GlobalResp.suc(o));
        }
        return GlobalResp.suc(o);
    }
}
