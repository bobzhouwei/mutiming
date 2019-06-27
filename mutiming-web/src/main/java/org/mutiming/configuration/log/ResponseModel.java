package org.mutiming.configuration.log;

/**
 * Response model for customized log
 *
 * @author Wei.Zhou
 */
public class ResponseModel {
    private static final ThreadLocal<ResponseModel> RESPONSE_MODEL = new ThreadLocal<>();
    /**
     * Response
     **/
    private String responseBody;

    public static ResponseModel getResponseModel() {
        return RESPONSE_MODEL.get();
    }

    public static void setResponseModel(ResponseModel response) {
        RESPONSE_MODEL.set(response);
    }

    public static void removeRequestModel() {
        RESPONSE_MODEL.remove();
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
