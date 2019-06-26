package org.mutiming.entity.valueobject.base;

public class ResponseUtility {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Response success() {
        return new Response()
                .setCode(ResponseCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    public static <T> Response success(T data) {
        return new Response<T>()
                .setCode(ResponseCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Response fail(String message) {
        return new Response()
                .setCode(ResponseCode.FAIL)
                .setMessage(message);
    }

    public static Response fail(int code, String message) {
        return new Response()
                .setCode(code)
                .setMessage(message);
    }

    public static Response fail(ResponseCode code) {
        return new Response()
                .setCode(code)
                .setMessage(code.getMessage());
    }

}