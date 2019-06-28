package org.mutiming.infrastructure.repository.trade;

import lombok.extern.slf4j.Slf4j;
import org.mutiming.common.utils.BigDecimalUtil;
import org.mutiming.entity.domainobject.config.ItemPriceInfo;
import org.mutiming.entity.valueobject.base.Response;
import org.mutiming.entity.valueobject.base.ResponseCode;
import org.mutiming.entity.valueobject.base.ResponseUtility;
import org.mutiming.entity.valueobject.trade.checkout.CheckOutResult;
import org.mutiming.store.domain.repository.TradeRepository;
import org.mutiming.store.domain.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of TradeRepository interfaces
 *
 * @author wei.zhou
 */
@Slf4j
@Component
public class TradeRepositoryImpl implements TradeRepository {
    private static final int MAX_ITEM_QUANTITY = 100000000;

    @Autowired
    private PriceService priceService;

    /**
     * Function:
     * - Input a list of watch Ids, give the total price.
     * <p>
     * Notes:
     * - List length can not exceed 100000000
     * - Spaces before or after id will be ignored.
     * - For example: id like ' 001', '001 '.' 001 ' will be regarded as '001';id like '00 1' will NOT be regarded as '001'
     * <p>
     * Error Code:
     * - 1000: Wrong Parameter
     * - 1001: Price list not exist
     * - 1002: No price for the input watch id
     * - 1003: Input list size exceeds the max value
     *
     * @param request purchasing item list
     * @return total price of the purchasing items or error info
     */
    @Override
    @SuppressWarnings("unchecked")
    public Response<CheckOutResult> checkout(List<String> request) {
        // Initialize the response
        CheckOutResult checkOutResult = new CheckOutResult();
        checkOutResult.setPrice(BigDecimal.ZERO);

        // Check for input parameters
        if (CollectionUtils.isEmpty(request)) {
            return ResponseUtility.success(checkOutResult);
        }

        if (request.size() > MAX_ITEM_QUANTITY) {
            return ResponseUtility.fail(
                    ResponseCode.EXCEED_MAX_SIZE.getCode(), ResponseCode.EXCEED_MAX_SIZE.getMessage() + MAX_ITEM_QUANTITY);
        }


        // Get the price list for all watches
        List<ItemPriceInfo> itemPriceList = priceService.getWatchPriceList();
        if (CollectionUtils.isEmpty(itemPriceList)) {
            return ResponseUtility.fail(ResponseCode.NO_WATCH_PRICE_LIST);
        }

        // Put the purchased watch Ids to map and count the number for each category
        Map<String, BigDecimal> itemMap = new HashMap<>(itemPriceList.size());
        for (String itemId : request) {
            if (StringUtils.isEmpty(itemId) || StringUtils.isEmpty(itemId.trim())) {
                continue;
            }
            itemId = itemId.trim();
            if (itemMap.containsKey(itemId)) {
                itemMap.put(itemId, itemMap.get(itemId).add(BigDecimal.ONE));
            } else {
                itemMap.put(itemId, BigDecimal.ONE);
            }
        }

        // Initialize the local variables for total price and total discount
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (Map.Entry<String, BigDecimal> itemEntry : itemMap.entrySet()) {
            String watchId = itemEntry.getKey();
            BigDecimal purchasedQuantity = itemEntry.getValue();

            Optional<ItemPriceInfo> watchPriceInfoOptional =
                    itemPriceList.stream().filter(itemPrice -> watchId.equals(itemPrice.getId())).findFirst();
            if (!watchPriceInfoOptional.isPresent()) {
                // Returns failure code and message if no price for the specified watch Id
                return ResponseUtility.fail(
                        ResponseCode.NO_WATCH_PRICE.getCode(), ResponseCode.NO_WATCH_PRICE.getMessage() + watchId);
            }

            // get the price and discount info
            ItemPriceInfo watchPriceInfo = watchPriceInfoOptional.get();
            BigDecimal unitPrice = watchPriceInfo.getUnitPrice();
            BigDecimal discountPackage = watchPriceInfo.getDiscountPackage();
            BigDecimal discountPrice = watchPriceInfo.getDiscountPrice();

            // calculate the price and add to the total price
            totalPrice = unitPrice.multiply(purchasedQuantity).add(totalPrice);

            if (discountPackage != null && discountPackage.compareTo(BigDecimal.ZERO) > 0
                    && discountPrice != null && discountPrice.compareTo(BigDecimal.ZERO) > 0) {
                // calculate the discount and add to the total discount
                BigDecimal discountQuantity = purchasedQuantity.divide(discountPackage, 0, RoundingMode.DOWN);
                totalDiscount = discountQuantity.multiply(discountPrice).add(totalDiscount);
            }
        }

        // calculate the actual price: totalPrice - totalDiscount
        checkOutResult.setPrice(totalPrice.subtract(totalDiscount));
        // prevent the negative price
        checkOutResult.setPrice(BigDecimalUtil.max(checkOutResult.getPrice(), BigDecimal.ZERO));

        return ResponseUtility.success(checkOutResult);
    }
}
