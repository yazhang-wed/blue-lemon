package org.lemon.lemoncrod.handler;

import java.util.Objects;

import org.lemon.lemoncommon.utils.AESUtils;
import org.lemon.lemoncrod.annotation.Encryption;
import org.lemon.lemoncrod.properties.EncryptProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
 * 加密拦截器
 * 返回响应结果之前调用此拦截器如果标注 {@link Encryption} 注解者执行加密逻辑
 * 该拦截器的处理顺序低于 {@link ResponseResultHandler}
 *
 * @author LBK
 * @create 2021-12-05 13:00
 */
@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
@Order(1)
public class EncryptionHandler implements ResponseBodyAdvice<Object> {

    private final ObjectMapper customizeObjectMapper;
    private final EncryptProperties encryptProperties;

    public EncryptionHandler(ObjectMapper customizeObjectMapper, EncryptProperties encryptProperties) {
        this.customizeObjectMapper = customizeObjectMapper;
        this.encryptProperties = encryptProperties;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encryption.class);
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 不为空则加密
        if (Objects.nonNull(body)) {
            byte[] keyBytes = encryptProperties.getKey().getBytes();
            body = AESUtils.encrypt(customizeObjectMapper.writeValueAsBytes(body), keyBytes);
        }
        return body;
    }
}
