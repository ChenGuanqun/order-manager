package order.manager.constant;

import lombok.Getter;

public enum RetureCode {
    // @formatter:off
    OK               (200, "ok"),
    BAD_REQUEST      (400, "bad request"),
    FORBIDDEN        (401, "forbidden"),
    PARAM_ERROR      (405, "param error"),
    SERVICE_ERROR    (503, "service unavailable");
    // @formatter:on

    private
    @Getter
    int code;
    private
    @Getter
    String msg;

    private RetureCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
