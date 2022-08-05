package com.thecms.compenont;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static Result success(String msg){
        return Result.success(200,msg,null);
    }

    public static Result fail(String msg){
        return Result.fail(400,msg,null);
    }

    public static Result success(Object object){
        return Result.success(200,"success",object);
    }

    public static Result fail(Object object){
        return Result.fail(400,"fail",object);
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
