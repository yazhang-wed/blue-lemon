package org.lemon.lemoncrod.common;

/**
 * 响应结果返回封装
 *
 * @author LBK
 * @create 2021-12-03 11:57
 */
public final class ResultResponse {

    private ResultResponse() {
    }

    /**
     * 成功只返回状态
     *
     * @return 成功返回不携带返回数据
     */
    public static Result<?> success() {
        return new Result<>(ResultCode.SUCCESS);
    }

    /**
     * 成功
     *
     * @param data 返回数据
     * @return 成功返回携带返回数据
     */
    public static Result<Object> success(Object data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    /**
     * 失败
     *
     * @param resultCode 失败状态码
     * @return 失败返回不携带返回数据
     */
    public static Result<?> failure(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    /**
     * @param resultCode 失败状态码
     * @param data       数据
     * @return 失败返回携带返回数据
     */
    public static Result<?> failure(ResultCode resultCode, Object data) {
        return new Result(resultCode, data);
    }
}
