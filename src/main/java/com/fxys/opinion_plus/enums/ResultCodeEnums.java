package com.fxys.opinion_plus.enums;

import com.fxys.opinion_plus.constants.*;


/**
 * @author :suyuzhou
 * @date :20210118
 * @description 参数返回枚举
 * @email :jack.su@jodoinc.com
 */
public enum ResultCodeEnums {

	/* 成功状态码 */
	INSERT_SUCCESS (ResponseCodeConstants.NO_ERR, "添加成功"),
	UPDATE_SUCCESS(ResponseCodeConstants.NO_ERR, "更新成功"),
	DELETE_SUCCESS(ResponseCodeConstants.NO_ERR, "删除成功"),
	CONNECTION_SUCCESS(ResponseCodeConstants.NO_ERR, "连接成功"),
	DEALWITH_SUCCESS(ResponseCodeConstants.NO_ERR, "处理成功"),
	MARK_SUCCESS (ResponseCodeConstants.NO_ERR, "标记成功"),
	UNMARK_SUCCESS (ResponseCodeConstants.NO_ERR, "撤销标记成功"),
	TRANSFER_SUCCESS (ResponseCodeConstants.NO_ERR, "转发成功"),
	/* 系统500错误*/
	SYSTEM_ERROR(ResponseCodeConstants.SYSTEM_ERROR, "系统异常，请稍后重试"),
	UNAUTHORIZED(ResponseCodeConstants.UNAUTHORIZED, "暂无权限，请向业务管理员或超级管理员申请权限"),

	/* 参数错误：10001-19999 */
	PARAM_IS_INVALID(ResponseCodeConstants.PARAM_IS_INVALID, "参数无效"),

	/* 用户错误：20001-29999*/
	USER_HAS_EXISTED(ResponseCodeConstants.USER_HAS_EXISTED, "用户已存在"),
	USER_NOT_FIND(ResponseCodeConstants.USER_NOT_FIND, "用户名不存在"),
	USER_ERROR(ResponseCodeConstants.USER_ERROR, "用户名或密码错误"),

	/*组别错误：30001-39999*/
	GROUP_EXISTED(ResponseCodeConstants.GROUP_EXISTED,"组名已存在，请勿重复添加"),
	INIT_FAIL(ResponseCodeConstants.INIT_FAIL,"初始化失败"),

	/*邮箱错误：40001-49999*/
	CONNECTION_FAIL(ResponseCodeConstants.CON_FAIL,"连接失败,请检查邮箱账号和密码是否出错"),
	MAILBOX_EXISTED(ResponseCodeConstants.MAILBOX_EXISTED,"邮箱已存在，请勿重复添加"),
	/*业务校验异常*/
	LOGIN_FAIL (ResponseCodeConstants.LOGIN_FAIL,"登录异常"),
	GET_FAIL(ResponseCodeConstants.GET_FAIL,"获取异常"),
	UPDATE_FAIL(ResponseCodeConstants.UPDATE_FAIL,"更新异常"),
	INSERT_FAIL(ResponseCodeConstants.INSERT_FAIL,"插入异常"),
	DELETE_FAIL(ResponseCodeConstants.DELETE_FAIL,"删除异常"),
	BUSINESS(ResponseCodeConstants.BUSINESS,"业务校验异常"),
	RULE_IS_EXISTED(ResponseCodeConstants.RULE_IS_EXISTED,"规则已经存在，请不要重复添加"),
	TRANSFER_FAIL(ResponseCodeConstants.TRANSFER_FAIL,"转发失败"),
	DEALWITH_FAIL(ResponseCodeConstants.DEALWITH_FAIL,"处理失败"),
	MARK_FAIL(ResponseCodeConstants.MARK_FAIL,"标记失败"),
	UNMARK_FAIL(ResponseCodeConstants.MARK_FAIL,"撤销标记失败"),
	PUSH_FAIL(ResponseCodeConstants.PUSH_FAIL,"消息推送失败"),
	MAILBOX_FAIL(ResponseCodeConstants.MAIL_FAIL,"邮箱为空"),
	PAGE_FAIL(ResponseCodeConstants.PAGE_FAIL, "分页异常");



	private Integer code;

	private String message;

	ResultCodeEnums(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer code() {
		return this.code;
	}

	public String message() {
		return this.message;
	}



}
