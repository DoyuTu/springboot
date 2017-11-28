package com.doyutu.exception;


public class MyException extends RuntimeException {

    private String msg = "";
    private ResultEnum codeEnum = ResultEnum.ERROR;

    public MyException() {
        super();
    }

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MyException(String msg, ResultEnum code) {
        super(msg);
        this.msg = msg;
        this.codeEnum = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultEnum getCodeEnum() {
        return codeEnum;
    }

    public void setCodeEnum(ResultEnum codeEnum) {
        this.codeEnum = codeEnum;
    }
}
