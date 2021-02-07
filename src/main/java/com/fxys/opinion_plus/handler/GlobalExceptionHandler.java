package com.fxys.opinion_plus.handler;

import com.fxys.opinion_plus.enums.ResultCodeEnums;
import com.fxys.opinion_plus.exception.OpinionException;
import com.fxys.opinion_plus.resp.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author :liangjiajun
 * @date :20210118
 * @description 通用异常处理器
 * @email :jack.su@jodoinc.com
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理运行时异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResult handleThrowable(Throwable e, HttpServletRequest request) {
        //TODO 运行是异常，可以在这里记录，用于发异常邮件通知
        ErrorResult error =ErrorResult.fail(ResultCodeEnums.SYSTEM_ERROR, e);
        log.error("URL:{} ,系统异常: ",request.getRequestURI(), e);
        return error;
    }

	/**
     * 处理自定义异常
     */
	@ExceptionHandler(OpinionException.class)
	public ErrorResult handleBusinessException(OpinionException e, HttpServletRequest request) {
        ErrorResult error = ErrorResult.builder().status(e.getCode())
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .build();
        log.warn("URL:{} ,业务异常:{}", request.getRequestURI(),error);
        return error;
	}

    /**
     * validator 统一异常封装
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String msgs = this.handle(e.getBindingResult().getFieldErrors());
        ErrorResult error = ErrorResult.fail(ResultCodeEnums.PARAM_IS_INVALID, e,  msgs);
        log.warn("URL:{} ,参数校验异常:{}", request.getRequestURI(),msgs);
        return error;
    }

    private String handle(List<FieldError> fieldErrors) {
        StringBuilder sb = new StringBuilder();
        for (FieldError obj : fieldErrors) {


            sb.append(obj.getDefaultMessage());

        }
		return sb.append("!!").toString();
    }

    /**
     * Assert的异常统一封装
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        ErrorResult error = ErrorResult.builder().status(ResultCodeEnums.BUSINESS.code())
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .build();
        log.warn("URL:{} ,业务校验异常:{}", request.getRequestURI(),e);
        return error;
    }

}
