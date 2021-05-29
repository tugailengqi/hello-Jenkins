package com.lengqi.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author haoxr
 * @date 2020-06-23
 **/
@Data
public class ResultVo<T> implements Serializable {

    private String code;

    private T data;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer total;

    public static <T> ResultVo<T> success() {
        return success(null);
    }

    public static <T> ResultVo<T> success(T data) {
        ResultCode rce = ResultCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            rce = ResultCode.SYSTEM_EXECUTION_ERROR;
        }
        return ResultVo(rce, data);
    }


    public static <T> ResultVo<T> success(T data, Long total) {
        ResultVo<T> ResultVo = new ResultVo();
        ResultVo.setCode(ResultCode.SUCCESS.getCode());
        ResultVo.setMsg(ResultCode.SUCCESS.getMsg());
        ResultVo.setData(data);
        ResultVo.setTotal(total.intValue());
        return ResultVo;
    }

    public static <T> ResultVo<T> failed() {
        return ResultVo(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), ResultCode.SYSTEM_EXECUTION_ERROR.getMsg(), null);
    }

    public static <T> ResultVo<T> failed(String msg) {
        return ResultVo(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), msg, null);
    }

    public static <T> ResultVo<T> judge(boolean status) {
        if (status) {
            return success();
        } else {
            return failed();
        }
    }

    public static <T> ResultVo<T> failed(IResultCode ResultCode) {
        return ResultVo(ResultCode.getCode(), ResultCode.getMsg(), null);
    }

    private static <T> ResultVo<T> ResultVo(IResultCode ResultCode, T data) {
        return ResultVo(ResultCode.getCode(), ResultCode.getMsg(), data);
    }

    private static <T> ResultVo<T> ResultVo(String code, String msg, T data) {
        ResultVo<T> ResultVo = new ResultVo<T>();
        ResultVo.setCode(code);
        ResultVo.setData(data);
        ResultVo.setMsg(msg);
        return ResultVo;
    }


    public static boolean isSuccess(ResultVo ResultVo) {
        if(ResultVo!=null&&ResultCode.SUCCESS.getCode().equals(ResultVo.getCode())){
            return true;
        }
        return false;
    }
}
