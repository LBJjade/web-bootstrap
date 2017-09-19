package com.becheer.donation.model.base;

/*
* 响应基类
* */
public class ResponseDto<T> {
    private T result;

    private int code;

    private String msg;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseDto(int code, String msg, T t){
        this.code=code;
        this.msg=msg;
        this.result=t;
    }

    public ResponseDto(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public static  <T>ResponseDto GetResponse(int code, String msg, T t){
        return new ResponseDto<T>(code,msg,t);
    }

    public static ResponseDto GetResponse(int code,String msg){
        return new ResponseDto(code,msg);
    }
}
