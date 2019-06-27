package org.mutiming.entity.domainobject.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * ItemPriceInfo model
 *
 * @author wei.zhou
 */
@Getter
@Setter
public class ItemPriceInfo {
    @ApiModelProperty(value = "Item Id")
    @JsonProperty("id")
    private String id;

    @ApiModelProperty(value = "Item name")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(value = "Unit price")
    @JsonProperty("unitPrice")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "Package set for discount")
    @JsonProperty("discountPackage")
    private BigDecimal discountPackage;

    @ApiModelProperty(value = "Discount Price for each discount package set")
    @JsonProperty("discountPrice")
    private BigDecimal discountPrice;
}
