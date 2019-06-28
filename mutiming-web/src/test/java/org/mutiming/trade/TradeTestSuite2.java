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
import java.util.ArrayList;
import java.util.List;

/**
 * Trade checkout service test suite 2 - negative cases to check the error handling
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTestSuite2 {
    @Autowired
    private TradeService tradeService;

    @Test
    public void empty_list_total_0() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.EMPTY_LIST_TOTAL_0, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(0), response.getData().getPrice());
    }

    @Test
    public void empty_id_total_0() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.EMPTY_ID_TOTAL_0, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(0), response.getData().getPrice());
    }

    @Test
    public void blank_id_total_0() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.BLANK_ID_TOTAL_0, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(0), response.getData().getPrice());
    }

    @Test
    public void test_null_input() throws Exception {
        Response<CheckOutResult> response = tradeService.checkout(null);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(0), response.getData().getPrice());
    }

    @Test
    public void test_empty_input() throws Exception {
        Response<CheckOutResult> response = tradeService.checkout(new ArrayList<>());
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(ResponseCode.SUCCESS.getCode(), response.getCode());
        Assert.assertEquals(BigDecimal.valueOf(0), response.getData().getPrice());
    }

    @Test
    public void id_not_found_1() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.ID_NOT_FOUND_1, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(ResponseCode.NO_WATCH_PRICE.getCode(), response.getCode());
    }

    @Test
    public void id_not_found_2() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.ID_NOT_FOUND_2, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(ResponseCode.NO_WATCH_PRICE.getCode(), response.getCode());
    }

    @Test
    public void id_not_found_3() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.ID_NOT_FOUND_3, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(ResponseCode.NO_WATCH_PRICE.getCode(), response.getCode());
    }

    @Test
    public void id_not_found_4() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.ID_NOT_FOUND_4, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(ResponseCode.NO_WATCH_PRICE.getCode(), response.getCode());
    }

    @Test
    public void id_not_found_space_ithe_middle() throws Exception {
        List<String> request = JSON.parseArray(TradeTestData.ID_NOT_FOUND_SPACE_ITHE_MIDDLE, String.class);
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(ResponseCode.NO_WATCH_PRICE.getCode(), response.getCode());
    }

    @Test
    public void exceed_max_size() throws Exception {
        List<String> request = new ArrayList<>();
        for (int i = 0; i < 100000001; i++) {
            request.add("001");
        }
        Response<CheckOutResult> response = tradeService.checkout(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(ResponseCode.EXCEED_MAX_SIZE.getCode(), response.getCode());
    }
}
