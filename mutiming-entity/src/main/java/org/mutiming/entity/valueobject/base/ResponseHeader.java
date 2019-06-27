package org.mutiming.entity.valueobject.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * ResponseHeader model
 * @author Wei.Zhou
 */
@Data
@Builder
public class ResponseHeader {
    @ApiModelProperty("Response Code")
    @JsonProperty("code")
    private int code;
    @ApiModelProperty("Response Message")
    @JsonProperty("message")
    private String message;
}
