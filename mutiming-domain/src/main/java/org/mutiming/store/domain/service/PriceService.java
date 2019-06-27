package org.mutiming.store.domain.service;

import org.mutiming.entity.domainobject.config.ItemPriceInfo;
import org.mutiming.store.domain.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    public List<ItemPriceInfo> getWatchPriceList() {
        return priceRepository.getWatchPriceList();
    }
}
