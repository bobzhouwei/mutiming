package org.mutiming.entity.valueobject.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    @ApiModelProperty("Response head")
    @JsonProperty("header")
    private ResponseHeader header;
    @ApiModelProperty("Response body")
    @JsonProperty("body")
    private T body;

    public Response() {
        this.header = ResponseHeader.builder().build();
    }

    @JsonIgnore
    public int getCode() {
        return this.header.getCode();
    }

    public Response setCode(ResponseCode responseCode) {
        this.header.setCode(responseCode.getCode());
        return this;
    }

    public Response setCode(int code) {
        this.header.setCode(code);
        return this;
    }

    @JsonIgnore
    public String getMessage() {
        return this.header.getMessage();
    }

    public Response<T> setMessage(String message) {
        this.header.setMessage(message);
        return this;
    }

    @JsonIgnore
    public T getData() {
        return body;
    }

    public Response<T> setData(T data) {
        this.body = data;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Response{");
        sb.append("code=").append(this.header.getCode());
        sb.append(", message='").append(this.header.getMessage()).append('\'');
        sb.append(", body=").append(body);
        sb.append('}');
        return sb.toString();
    }
}