package org.mutiming.trade;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mutiming.entity.valueobject.base.Response;
import org.mutiming.entity.valueobject.base.ResponseCode;
import org.mutiming.entity.valueobject.trade.checkout.CheckOutResult;
import org.mutiming.store.domain.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Trade checkout service test suite 2 - large number of  purchase items cases to check the capabilities
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTestSuite3 {
    @Autowired
    private TradeService tradeService;

    @Test
    public void test_40000000_items() throws Exception {
        List<String> request = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            request.add("001");
            request.add("002");
            request.add("003");
            request.add("004");
        }
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        BigDecimal totalPrice = BigDecimal.valueOf(3333333).multiply(BigDecimal.valueOf(200))
                .add(BigDecimal.valueOf(1).multiply(BigDecimal.valueOf(100)))
                .add(BigDecimal.valueOf(5000000).multiply(BigDecimal.valueOf(120)))
                .add(BigDecimal.valueOf(10000000).multiply(BigDecimal.valueOf(50)))
                .add(BigDecimal.valueOf(10000000).multiply(BigDecimal.valueOf(30)));
        Assert.assertEquals(totalPrice, response.getData().getPrice());
    }

    @Test
    public void test_100000000_items() throws Exception {
        List<String> request = new ArrayList<>();
        for (int i = 0; i < 100000000; i++) {
            request.add("001");
        }
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        BigDecimal totalPrice = BigDecimal.valueOf(33333333).multiply(BigDecimal.valueOf(200))
                .add(BigDecimal.valueOf(1).multiply(BigDecimal.valueOf(100)));
        Assert.assertEquals(totalPrice, response.getData().getPrice());
    }

}
