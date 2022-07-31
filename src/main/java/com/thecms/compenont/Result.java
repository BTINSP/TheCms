package com.thecms.compenont;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data){
        return Result.success(200,"success",data);
    }

    public static Result fail(Object data){
        return Result.fail(400,"fail",data);
    }

    public static Result success(int code, String msg,Object data){
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static Result fail(int code, String msg,Object data){
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }



}
