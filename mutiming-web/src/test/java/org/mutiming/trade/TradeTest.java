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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTest {
    @Autowired
    private TradeService tradeService;

    @Test
    public void no_discount_total_200() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.NO_DISCOUNT_TOTAL_200, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(200), response.getData().getPrice());
    }

    @Test
    public void discount_3_for_200() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.DISCOUNT_3_FOR_200, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(200), response.getData().getPrice());
    }

    @Test
    public void discount_total_300() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.DISCOUNT_TOTAL_300, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(300), response.getData().getPrice());
    }

    @Test
    public void discount_total_600() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.DISCOUNT_TOTAL_600, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(600), response.getData().getPrice());
    }

    @Test
    public void watch_002_1_for_80() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.WATCH_002_1_FOR_80, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(80), response.getData().getPrice());
    }

    @Test
    public void watch_002_discount_2_for_120() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.WATCH_002_DISCOUNT_2_FOR_120, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(120), response.getData().getPrice());
    }

    @Test
    public void watch_002_3_total_200() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.WATCH_002_3_TOTAL_200, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(200), response.getData().getPrice());
    }

    @Test
    public void multiple_category_no_discount_total_330() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.MULTIPLE_CATEGORY_NO_DISCOUNT_TOTAL_330, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(330), response.getData().getPrice());
    }

    @Test
    public void multiple_category_discount_total_320() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.MULTIPLE_CATEGORY_DISCOUNT_TOTAL_320, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(320), response.getData().getPrice());
    }
}
