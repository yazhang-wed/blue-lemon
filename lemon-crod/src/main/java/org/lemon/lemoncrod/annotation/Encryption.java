package org.lemon.lemoncrod.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.lemon.lemoncrod.handler.EncryptionHandler;


/**
 * 加密注解
 * 如果返回接口上标注此注解标记加密返回
 * 处理该主键的拦截器为 {@link EncryptionHandler}
 * @author LBK
 * @create 2021-12-05 12:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Encryption {
}
