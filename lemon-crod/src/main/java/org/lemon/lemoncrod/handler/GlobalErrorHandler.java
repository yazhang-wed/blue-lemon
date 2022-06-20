package org.lemon.lemoncrod.handler;

import java.util.Map;

import org.lemon.lemoncrod.common.Result;
import org.lemon.lemoncrod.common.ResultCode;
import org.lemon.lemoncrod.common.ResultResponse;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;


/**
 * 统一处理错误返回
 *
 * @author LBK
 * @create 2021-12-03 15:02
 */
@Component
@Slf4j
public class GlobalErrorHandler extends DefaultErrorAttributes {

    /**
     * 自定义错误返回格式
     *
     * @param webRequest
     * @param options
     * @return
     */
    @Override
    public BeanMap getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("异常原因", "Jwt令牌解析失败，请输入正确的Jwt令牌!");
        Result<?> result = ResultResponse.failure(ResultCode.NOT_FOUND, errorAttributes);
        log.error(errorAttributes.get("message").toString(), errorAttributes.get("trace"));
        return BeanMap.create(result);
    }
}
