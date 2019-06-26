package org.mutiming.entity.valueobject.trade.checkout;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CheckOutResult {
    @ApiModelProperty(value = "Total Price of the purchase items", required = true)
    @JsonProperty("price")
    private BigDecimal price;
}
