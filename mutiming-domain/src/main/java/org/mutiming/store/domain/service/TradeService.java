package org.mutiming.store.domain.service;

import org.mutiming.entity.valueobject.base.Response;
import org.mutiming.entity.valueobject.trade.checkout.CheckOutResult;
import org.mutiming.store.domain.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Trade Service
 *
 * @author wei.zhou
 */
@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    public Response<CheckOutResult> checkout(List<String> request) {
        return tradeRepository.checkout(request);
    }
}
