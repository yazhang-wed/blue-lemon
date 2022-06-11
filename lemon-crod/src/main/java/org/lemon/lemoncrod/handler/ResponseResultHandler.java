package org.lemon.lemoncrod.handler;

import java.util.Objects;

import org.lemon.lemoncrod.annotation.EncapsulationTemplates;
import org.lemon.lemoncrod.annotation.NoEncapsulatedTemplate;
import org.lemon.lemoncrod.common.Result;
import org.lemon.lemoncrod.common.ResultResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

/**
 * 封装返回结果拦截器
 * 如果接口方法上标注了{@link NoEncapsulatedTemplate}该注解，表示不执行封装逻辑；<br />
 * 如果接口方法或所在类上标注了 {@link EncapsulationTemplates} 注解，表示执行封装逻辑；<br />
 * 如果{@link EncapsulationTemplates}注解标注在类上表示表示整个类的返回值都需要进行封装； <br />
 * <b>注意：{@link NoEncapsulatedTemplate} 注解的权重要高于 {@link EncapsulationTemplates} 注解的权重</b>
 * <p>
 * 该拦截器的处理顺序低于 {@link EncryptionHandler} 拦截器
 *
 * @author LBK
 * @create 2021-12-03 12:07
 */
@ControllerAdvice
@Order(2)
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    private final ObjectMapper customizeObjectMapper;

    public ResponseResultHandler(ObjectMapper customizeObjectMapper) {
        this.customizeObjectMapper = customizeObjectMapper;
    }

    /**
     * 是否请求包含了包装注解,或者标注了不包装注解
     *
     * @param returnType    返回类型
     * @param converterType 选择的转换器类型
     * @return 此处如果返回false, 则不执行当前Advice的业务
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 判断请求接口上是否标注不封装返回模板注解
        if (returnType.hasMethodAnnotation(NoEncapsulatedTemplate.class)) {
            return false;
        }

        // 判断请求接口上是否标注封装返回模板，或者接口的类上标注封装返回模板
        return returnType.hasMethodAnnotation(EncapsulationTemplates.class) ||
                returnType.getDeclaringClass().isAnnotationPresent(EncapsulationTemplates.class);
    }

    /**
     * 处理是否封装进统一返回模板中
     *
     * @param body                  返回的内容
     * @param returnType            返回类型
     * @param selectedContentType   返回内容的类型
     * @param selectedConverterType 写入响应的转换器类型
     * @param request               Java原生请求对象
     * @param response              Java原生响应
     * @return 封装好的模板对象
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // 判断返回的媒体类型是否为 application/json 类型
        if (selectedContentType.isCompatibleWith(MediaType.APPLICATION_JSON)) {
            if (Objects.equals(returnType.getGenericParameterType(), Result.class)) {
                return body;
            }
//            if (Objects.equals(returnType.getGenericParameterType(), String.class)) {
//                return customizeObjectMapper.writeValueAsString(ResultResponse.success(body));
//            }
            return ResultResponse.success(body);
        }
        return body;
    }
}
