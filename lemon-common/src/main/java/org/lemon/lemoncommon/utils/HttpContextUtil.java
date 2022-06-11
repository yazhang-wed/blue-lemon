package org.lemon.lemoncommon.utils;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author LBK
 * @create 2021-12-03 14:18
 */
public final class HttpContextUtil {

    private HttpContextUtil() {
    }

    /**
     * 获取请求的request
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
