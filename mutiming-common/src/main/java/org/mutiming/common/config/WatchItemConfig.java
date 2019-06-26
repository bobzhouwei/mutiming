package org.mutiming.common.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mutiming.entity.domainobject.config.ItemPriceInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Wei.Zhou on 2019/6/26
 * Config for watches
 */
@Slf4j
@Configuration
public class WatchItemConfig {
    private static List<ItemPriceInfo> watchItemPriceList = null;
    @Value("${priceInfo.watchPriceList}")
    private String watchPriceListConfigString;

    public List<ItemPriceInfo> getWatchPriceListConfig() {
        if (watchItemPriceList != null) {
            // Get from local cache
            return watchItemPriceList;
        }

        if (StringUtils.isEmpty(this.watchPriceListConfigString)) {
            return new ArrayList<>();
        }

        try {
            // Get and parse from application config
            watchItemPriceList = JSON.parseArray(this.watchPriceListConfigString, ItemPriceInfo.class);
            return watchItemPriceList;
        } catch (Exception e) {
            log.error("getWatchPriceListConfig", e);
            return new ArrayList<>();
        }
    }
}
