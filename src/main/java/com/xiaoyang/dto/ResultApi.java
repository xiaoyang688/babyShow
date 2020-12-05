package com.xiaoyang.dto;

/**
 * @author xiaoyang
 * @create 2020/12/5 8:58 下午
 */
public class ResultApi {

    private String message;

    private Integer code;

    public ResultApi(){
        this.message = "执行成功！";
        this.code = 200 ;
    }

    public ResultApi(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
