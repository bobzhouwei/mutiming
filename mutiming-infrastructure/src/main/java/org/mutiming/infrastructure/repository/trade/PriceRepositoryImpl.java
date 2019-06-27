package org.mutiming.infrastructure.repository.trade;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mutiming.common.config.PriceInfoConfig;
import org.mutiming.entity.domainobject.config.ItemPriceInfo;
import org.mutiming.store.domain.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PriceRepositoryImpl implements PriceRepository {
    private static List<ItemPriceInfo> watchItemPriceList = null;

    private final PriceInfoConfig priceInfoConfig;

    @Autowired
    private PriceRepositoryImpl(PriceInfoConfig priceInfoConfig) {
        this.priceInfoConfig = priceInfoConfig;
    }


    @Override
    public List<ItemPriceInfo> getWatchPriceList() {
        if (watchItemPriceList != null) {
            // Get from local cache
            return watchItemPriceList;
        }

        String watchPriceListConfig = priceInfoConfig.getWatchPriceListConfig();
        if (StringUtils.isEmpty(watchPriceListConfig)) {
            return new ArrayList<>();
        }

        try {
            // Get and parse from application config
            watchItemPriceList = JSON.parseArray(watchPriceListConfig, ItemPriceInfo.class);
            return watchItemPriceList;
        } catch (Exception e) {
            log.error("getWatchPriceListConfig", e);
            return new ArrayList<>();
        }
    }
}
