package org.lemon.lemoncrod.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.lemon.lemoncrod.handler.DeclassifyHandler;


/**
 * 解密注解
 * 如果返回接口上标注此注解解密返回
 * 处理该主键的拦截器为 {@link DeclassifyHandler}
 *
 * @author LBK
 * @create 2021-12-05 12:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface Declassify {
}
