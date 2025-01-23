package com.gaecoli.datavis.utils;

import lombok.Data;

public class ResponseUtils {
    private enum ResponseCode {
        OK(1001),
        ERROR(1002),
        NOT_FOUND(1003),

        CHECK_ERROR(1004),
        UNAUTHORIZED(2001);


        public final int code;

        ResponseCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    @Data
    public static class Response {
        ResponseCode code;

        String msg;

        Object data;

        public Response(ResponseCode _code, String _msg, Object _data) {
            this.code = _code;
            this.msg = _msg;
            this.data = _data;
        }

        public int getCode() {
            return code.getCode();
        }
    }

    public static Response Success(String msg) {
        return new Response(ResponseCode.OK, msg, null);
    }

    public static Response Success(String msg, Object data) {
        return new Response(ResponseCode.OK, msg, data);
    }

    public static Response Error(String msg) {
        return new Response(ResponseCode.ERROR, msg, null);
    }

    public static Response NotFound(String msg) {
        return new Response(ResponseCode.NOT_FOUND, msg, null);
    }

    public static Response Unauthorized(String msg) {
        return new Response(ResponseCode.UNAUTHORIZED, msg, null);
    }

    public static Response CheckValid(Object data) {
        return new Response(ResponseCode.CHECK_ERROR, "check error", data);
    }
}
