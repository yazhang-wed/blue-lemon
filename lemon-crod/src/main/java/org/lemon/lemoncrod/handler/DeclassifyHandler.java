package org.lemon.lemoncrod.handler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import org.lemon.lemoncommon.utils.AESUtils;
import org.lemon.lemoncrod.annotation.Declassify;
import org.lemon.lemoncrod.properties.EncryptProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

/**
 * 解密拦截器
 * <p>
 * 调用目标接口之前执行此拦截器，如果标注 {@link Declassify} 注解则执行解密流程
 *
 * @author LBK
 * @create 2021-12-05 13:08
 */
@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
@Order(1)
public class DeclassifyHandler extends RequestBodyAdviceAdapter {

    private final EncryptProperties encryptProperties;

    public DeclassifyHandler(EncryptProperties encryptProperties) {
        this.encryptProperties = encryptProperties;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 校验方法或参数上有解密注解
        return methodParameter.hasMethodAnnotation(Declassify.class) || methodParameter.hasParameterAnnotation(Declassify.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage,
                                           MethodParameter parameter,
                                           Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        byte[] body = new byte[inputMessage.getBody().available()];
        inputMessage.getBody().read(body);
        try {
            byte[] decrypt = AESUtils.decrypt(body, encryptProperties.getKey().getBytes());
            return new HttpInputMessage() {
                @Override
                public InputStream getBody() {
                    return new ByteArrayInputStream(decrypt);
                }

                @Override
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
    }
}
