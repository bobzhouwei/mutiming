package org.mutiming.common.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Config for watch price list
 *
 * @author Wei.Zhou on 2019/6/26
 */
@Slf4j
@Getter
@Configuration
public class PriceInfoConfig {
    @Value("${priceInfo.watchPriceList}")
    private String watchPriceListConfig;

}
