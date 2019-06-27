package org.mutiming.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mutiming.entity.valueobject.base.Response;
import org.mutiming.entity.valueobject.trade.checkout.CheckOutResult;
import org.mutiming.store.domain.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Trade Controller
 *
 * @author wei.zhou on 2019/6/27
 */
@RestController
@RequestMapping("/")
@Api(description = "Trade Controller")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @PostMapping("/checkout")
    @ApiOperation(value = "checkout")
    public Response<CheckOutResult> checkout(@RequestBody @ApiParam("checkout") List<String> request) {
        return tradeService.checkout(request);
    }

}
