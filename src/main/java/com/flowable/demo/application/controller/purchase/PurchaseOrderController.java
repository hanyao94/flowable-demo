/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	ModuleController.java
 * 模块说明：
 * 修改历史：
 * 2021/2/5 - seven - 创建。
 */
package com.flowable.demo.application.controller.purchase;

import com.flowable.demo.domain.service.PurchaseOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author seven
 */

@Api(tags = "采购单服务")
@RestController
@RequestMapping(value = "{tenant}/purchase-order", produces = "application/json;charset=utf-8")
public class PurchaseOrderController {

  @Autowired
  private PurchaseOrderService purchaseOrderService;

  @ApiOperation(value = "创建采购单")
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String create(@ApiParam(required = true) @PathVariable("tenant") String tenant,
                       @ApiParam(required = true, value = "采购单") @RequestBody PurchaseOrder purchaseOrder,
                       @ApiParam(required = true, value = "操作上下文") @RequestParam("operator") String operator) {

    return purchaseOrderService.create(purchaseOrder, operator);
  }

  @ApiOperation(value = "获取采购单")
  @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
  public PurchaseOrder get(@ApiParam(required = true) @PathVariable("tenant") String tenant,
                           @ApiParam(required = true, value = "采购单ID") @PathVariable("orderId") String orderId) {

    return purchaseOrderService.get(tenant, orderId);
  }

  @ApiOperation(value = "提交采购单")
  @RequestMapping(value = "/submit", method = RequestMethod.POST)
  public String submit(@ApiParam(required = true) @PathVariable("tenant") String tenant,
                       @ApiParam(required = true, value = "采购单ID") @RequestParam("purchaseOrderId") String purchaseOrderId,
                       @ApiParam(required = true, value = "操作上下文") @RequestParam("operator") String operator) {

    purchaseOrderService.submit(tenant, purchaseOrderId, operator);
    return purchaseOrderId;
  }

  @ApiOperation(value = "审核通过")
  @RequestMapping(value = "/accepted", method = RequestMethod.POST)
  public String accepted(@ApiParam(required = true) @PathVariable("tenant") String tenant,
                         @ApiParam(required = true, value = "采购单ID") @RequestParam("purchaseOrderId") String purchaseOrderId,
                         @ApiParam(required = true, value = "操作上下文") @RequestParam("operator") String operator) {

    purchaseOrderService.accepted(tenant, purchaseOrderId, operator);
    return purchaseOrderId;
  }

  @ApiOperation(value = "审核驳回")
  @RequestMapping(value = "/rejected", method = RequestMethod.POST)
  public String rejected(@ApiParam(required = true) @PathVariable("tenant") String tenant,
                         @ApiParam(required = true, value = "采购单ID") @RequestParam("purchaseOrderId") String purchaseOrderId,
                         @ApiParam(required = true, value = "操作上下文") @RequestParam("operator") String operator) {

    purchaseOrderService.rejected(tenant, purchaseOrderId, operator);
    return purchaseOrderId;
  }

  @ApiOperation(value = "查询待审核")
  @RequestMapping(value = "/query-for-approve", method = RequestMethod.GET)
  public List<PurchaseOrder> queryForApproving(@ApiParam(required = true) @PathVariable("tenant") String tenant,
                                               @ApiParam(required = true, value = "操作人") @RequestParam("operator") String operator) {
    return purchaseOrderService.query(tenant, operator);
  }

}
