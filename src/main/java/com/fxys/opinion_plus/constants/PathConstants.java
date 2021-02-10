package com.fxys.opinion_plus.constants;
/**
 * @author :suyuhzou
 * @date :20210119
 * @description 路径常量
 * @email :jack.su@jodoinc.com
 */
public class PathConstants {
    /**
     * 用户相关操作
     */
    public static final String USER_SING_IN="/api/user/login";
    public static final String USER_REGISTER="/api/user/register";
    public static final String USER_UPDATE_USERNAME="/api/user/updateUsername";
    public static final String USER_UPDATE_EMAIL="/api/user/updateEmail";
    public static final String USER_UPDATE_PASSWORD="/api/user/updatePassword";
    public static final String USER_GET="/api/user/get";

    /**
     * 日志相关操作
     */
    public static final String LOG_SEARCH="/api/log/getByPage";

    /**
     * 关键字相关操作
     */
    public static final String KEYWORD_SEARCH="/api/keyword/getByPage";
    public static final String KEYWORD_ADD="/api/keyword/add";
    public static final String KEYWORD_GET="/api/keyword/get";
    public static final String KEYWORD_UPDATE="/api/keyword/update";
    public static final String KEYWORD_DELETE="/api/keyword/delete";
    public static final String KEYWORD_EXPORT="/api/keyword/exportMeg";


    /**
     * 微博相关操作
     */
    public static final String BLOG_EVENT="/api/blog/getEvent";
    public static final String BLOG_SENSITIVE="/api/blog/getSensitive";
    public static final String BLOG_POSITIVE="/api/blog/getPositive";
    public static final String BLOG_ORIGIN="/api/blog/getOrigin";
}
