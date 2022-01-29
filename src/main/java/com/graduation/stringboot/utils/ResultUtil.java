package com.graduation.stringboot.utils;

import com.graduation.stringboot.entity.Result;

/**
 * 结果工具类
 *
 * @author Lyn
 * @date 2022/01/29
 */
public class ResultUtil {

    /**
     * 成功成功且带数据
     *
     * @param o 响应数据
     * @return {@link Result}
     **/
    public static Result success(Object o) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMessage());
        result.setData(o);
        System.out.println(result.toString());
        return result;
    }

    /**
     * 成功成功但不带数据
     *
     * @return {@link Result}
     **/
    public static Result success() {
        return success(null);
    }

    /**
     * 错误失败且带数据
     *
     * @param o 响应数据
     * @return {@link Result}
     **/
    public static Result error(Object o) {
        Result result = new Result();
        result.setCode(ResultEnum.FAILURE.getCode());
        result.setMsg(ResultEnum.FAILURE.getMessage());
        result.setData(o);
        return result;
    }

    /**
     * 错误失败但不带数据
     *
     * @return {@link Result}
     **/
    public static Result error() {
        return error(null);
    }

    /**
     * 自定义状态码并带返回数据
     *
     * @param re 自定义状态码
     * @param o  响应数据
     * @return {@link Result}
     */
    public static Result error(ResultEnum re, Object o) {
        Result result = new Result();
        result.setResultEnum(re);
        result.setData(o);
        return result;
    }

    /**
     * 自定义状态码但不返回数据
     *
     * @param re 自定义状态码
     * @return {@link Result}
     */
    public static Result error(ResultEnum re) {
        return error(re, null);
    }
}
