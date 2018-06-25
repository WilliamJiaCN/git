package com.hivescm.tms.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 属性变更日志信息
 *
 * @author 李洪春
 * @since 2017/7/27 15:03
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeLog {

    /**
     * 日志消息
     */
    String message();


    /**
     * 数据字典key值
     *
     * @return
     */
    String dictKey() default "";


}
