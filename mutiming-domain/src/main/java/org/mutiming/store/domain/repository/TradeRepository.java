package org.mutiming.store.domain.repository;

import org.mutiming.entity.valueobject.base.Response;
import org.mutiming.entity.valueobject.trade.checkout.CheckOutResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository {
    /**
     * Calculate the total price for the purchasing items
     *
     * @param request purchasing item list
     * @return total price
     */
    Response<CheckOutResult> checkout(List<String> request);
}
