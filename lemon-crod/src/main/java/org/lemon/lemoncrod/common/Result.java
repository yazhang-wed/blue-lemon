package org.lemon.lemoncrod.common;

import java.io.Serializable;

import lombok.Data;

/**
 * 统一 API 响应结果格式封装
 *
 * @author LBK
 * @create 2021-12-03 11:54
 */
@Data
public final class Result<T> implements Serializable {
    private static final long serialVersionUID = 6308315887056661996L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public Result() {

    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }
}
