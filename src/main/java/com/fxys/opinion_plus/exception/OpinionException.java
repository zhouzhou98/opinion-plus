package com.fxys.opinion_plus.exception;



import com.fxys.opinion_plus.enums.ResultCodeEnums;
import lombok.Data;

/**
 * @author :suyuzhou
 * @date :20201213
 * @description 邮件系统异常类
 * @email :jack.su@jodoinc.com
 */
@Data
public class OpinionException extends RuntimeException {

    private static final long serialVersionUID = 2450214686001409867L;

    protected Integer code;

    protected String message;

    public OpinionException(ResultCodeEnums resultCode) {
        this.code = resultCode.code();
        this.message =resultCode.message() ;
    }
}