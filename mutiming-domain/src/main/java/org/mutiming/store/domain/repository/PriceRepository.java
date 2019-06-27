package org.mutiming.store.domain.repository;

import org.mutiming.entity.domainobject.config.ItemPriceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository {
    /**
     * Get the price list includes discount info for the sale watches
     *
     * @return price list
     */
    List<ItemPriceInfo> getWatchPriceList();
}
