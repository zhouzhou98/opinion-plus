package com.fxys.opinion_plus.resp;


import com.fxys.opinion_plus.enums.ResultCodeEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fxys.opinion_plus.constants.*;
/**
 * @author :suyuzhou
 * @date :20210118
 * @description 通用返回参数类
 * @email :jack.su@jodoinc.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResp<T> {
  private Integer code ;
  private T data;

  public GlobalResp(T data) {
    this.data = data;
  }

  /**
   * 成功，创建ResResult：没data数据
   */
  public static GlobalResp suc() {
    GlobalResp resp = new GlobalResp();
    resp.setCode(ResponseCodeConstants.NO_ERR);
    return resp;
  }

  /**
   * 成功，创建ResResult：有data数据
   */
  public static GlobalResp suc(Object data) {
    GlobalResp resp = new GlobalResp();
    resp.setCode(ResponseCodeConstants.NO_ERR);
    resp.setData(data);
    return resp;
  }


  /**
   * 失败，指定status、desc
   */
  public static GlobalResp fail(Integer code, String msg) {
    GlobalResp result = new GlobalResp();
    result.setCode(code);
    result.setData(msg);
    return result;
  }

  /**
   * 失败，指定ResultCode枚举
   */
  public static GlobalResp fail(ResultCodeEnums resultCode) {
    GlobalResp result = new GlobalResp();
    result.setResultCode(resultCode);
    return result;
  }

  /**
   * 把ResultCode枚举转换为ResResult
   */
  private void setResultCode(ResultCodeEnums code) {
    this.code = code.code();
    this.data =(T)code.message();
  }
}
