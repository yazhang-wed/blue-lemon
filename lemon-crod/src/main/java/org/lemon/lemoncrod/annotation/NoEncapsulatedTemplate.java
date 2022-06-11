package org.lemon.lemoncrod.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.lemon.lemoncrod.handler.ResponseResultHandler;


/**
 * 标注该主键表示返回结果会封装到返回模板中
 * 该方法可以标注在方法 注意该注解权重高于 {@link EncapsulationTemplates} 注解
 * 处理该注解的拦截器为 {@link ResponseResultHandler}
 *
 * @author LBK
 * @create 2021-12-03 12:04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NoEncapsulatedTemplate {
}
