package org.mutiming.trade;

import com.alibaba.fastjson.JSON;
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
import java.util.List;

/**
 * Trade checkout service test suite 1 - positive cases to check the correctness of the price calculation
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTestIdWithSpaces {
    @Autowired
    private TradeService tradeService;

    @Test
    public void space_before_watch_002_1_for_80() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.SPACE_BEFORE_WATCH_002_1_FOR_80, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(80), response.getData().getPrice());
    }

    @Test
    public void space_after_watch_002_1_for_80() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.SPACE_AFTER_WATCH_002_1_FOR_80, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(80), response.getData().getPrice());
    }

    @Test
    public void space_before_and_after_watch_002_1_for_80() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.SPACE_BEFORE_AND_AFTER_WATCH_002_1_FOR_80, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(80), response.getData().getPrice());
    }
}
