package org.lemon.lemoncrod.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.lemon.lemoncrod.handler.ResponseResultHandler;


/**
 * 标注该主键表示返回结果会封装到返回模板中
 * 该方法可以标注在方法和类上
 * 处理该主键的拦截器为 {@link ResponseResultHandler}
 *
 * @author LBK
 * @create 2021-12-03 12:04
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncapsulationTemplates {
}

