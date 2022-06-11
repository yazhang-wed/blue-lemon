package org.lemon.lemoncrod.exception;

/**
 * 认证异常
 *
 * @author LBK
 * @create 2021-12-03 15:05
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}
