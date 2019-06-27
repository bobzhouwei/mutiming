package org.mutiming.entity.valueobject.base;

/**
 * The Centralized Response Code Definition
 *
 * @author Wei.Zhou
 */
public enum ResponseCode {
    SUCCESS(200, "Success"),
    FAIL(400, "Fail"),
    UNAUTHORIZED(401, "Unauthorized"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    WRONG_PARAMETER(1000, "Wrong Parameter"),
    NO_WATCH_PRICE_LIST(1001, "No price list for watch"),
    NO_WATCH_PRICE(1002, "No price for watch id: "),
    EXCEED_MAX_SIZE(1003, "Exceed the max size of: ");

    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * get message by code
     *
     * @param code code
     * @return message
     */
    public static String getMessage(int code) {
        for (ResponseCode responseCode : ResponseCode.values()) {
            if (code == responseCode.getCode()) {
                return responseCode.getMessage();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}