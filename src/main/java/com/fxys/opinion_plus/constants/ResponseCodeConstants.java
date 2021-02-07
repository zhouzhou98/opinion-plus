package com.fxys.opinion_plus.constants;

/**
 * @author :suyuzhou
 * @date :20210118
 * @description 返回状态码常量
 * @email :jack.su@jodoinc.com
 */
public class ResponseCodeConstants {

  public static final int NO_ERR = 0;

  /*业务异常*/
  public static final int BUSINESS=4000;
  public static final Integer LOGIN_FAIL =4001 ;
  public static final Integer GET_FAIL =4002;
  public static final Integer UPDATE_FAIL =4003 ;
  public static final Integer INSERT_FAIL = 4004;
  public static final Integer DELETE_FAIL = 4005;
  public static final Integer PAGE_FAIL = 4006;
  /*系统错误*/
  public static final int SYSTEM_ERROR=10000;


  /* 参数异常 */
  public static final int PARAM_IS_INVALID=10001;

  /* 用户错误：20001-29999*/
  public static final int USER_HAS_EXISTED=20001;
  public static final int USER_NOT_FIND=20002;
  public static final Integer USER_ERROR = 20003;
  public static final int UNAUTHORIZED=10401;



  /*组别错误：30001-39999*/
  public static final Integer GROUP_EXISTED = 30001;

  /*邮箱错误：40001-49999*/
  public static final Integer CON_FAIL = 40001;
  public static final Integer INIT_FAIL = 40002;
  public static final Integer TRANSFER_FAIL =40003 ;
  public static final Integer MAILBOX_EXISTED=40004;
  public static final Integer MAIL_FAIL = 40005;
  /*规则错误：50001-59999*/
  public static final Integer RULE_IS_EXISTED =50001 ;

  /*邮件错误：60001-69999*/
  public static final Integer DEALWITH_FAIL = 60001;
  public static final Integer MARK_FAIL = 60002;
  public static final Integer PUSH_FAIL = 60003;



}
