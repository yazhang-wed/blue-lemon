package org.lemon.lemoncrod.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lemon.lemoncrod.common.Result;
import org.lemon.lemoncrod.common.ResultCode;
import org.lemon.lemoncrod.common.ResultResponse;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;


/**
 * 统一处理错误返回
 *
 * @author LBK
 * @create 2021-12-03 15:02
 */
@Component
public class GlobalErrorHandler extends DefaultErrorAttributes {

    /**
     * 自定义错误返回页面
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return super.resolveException(request, response, handler, ex);
    }

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
        Result<?> result = ResultResponse.failure(ResultCode.NOT_FOUND, errorAttributes.get("path"));
        return BeanMap.create(result);
    }
}
