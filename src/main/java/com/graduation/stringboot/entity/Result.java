package com.graduation.stringboot.entity;

import com.graduation.stringboot.utils.Result.ResultEnum;

/**
 * 结果类
 *
 * @author Lyn
 * @date 2022/01/29
 */
public class Result{

    /**
     * 结果码
     */
    private Integer code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;

    public Result() {
        super();
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 根据状态码枚举设置状态码和状态信息的值
     * @param re 状态码枚举
     */
    public void setResultEnum(ResultEnum re) {
        this.code = re.getCode();
        this.msg = re.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
