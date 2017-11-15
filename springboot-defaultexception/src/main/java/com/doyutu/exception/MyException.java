package com.doyutu.exception;


public class MyException extends RuntimeException {

    private String msg;
    private ResultEnum code;

    public MyException(String msg, ResultEnum code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultEnum getCode() {
        return code;
    }

    public void setCode(ResultEnum code) {
        this.code = code;
    }
}
