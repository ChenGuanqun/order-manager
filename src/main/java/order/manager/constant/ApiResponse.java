/*
 * @(#) ApiResponse.java 2015年10月21日
 * 
 * Copyright 2010 NetEase.com, Inc. All rights reserved.
 */
package order.manager.constant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ApiResponse<T> {
    private
    @Getter
    @Setter
    int code;
    private
    @Getter
    @Setter
    String msg = "";
    private
    @Getter
    @Setter
    T result;

    public ApiResponse() {
        this(RetureCode.OK.getCode(), RetureCode.OK.getMsg(), null);
    }

    public ApiResponse(int code) {
        this(code, null, null);
    }

    public ApiResponse(T result) {
        this(RetureCode.OK.getCode(), RetureCode.OK.getMsg(), result);
    }

    public ApiResponse(int code, String msg) {
        this(code, msg, null);
    }

    public ApiResponse(RetureCode code) {
        this(code.getCode(), code.getMsg(), null);
    }

    public ApiResponse(RetureCode code, T result) {
        this(code.getCode(), code.getMsg(), result);
    }

    public ApiResponse(int code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
