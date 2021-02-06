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

import com.flowable.demo.application.controller.module.Module;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seven
 */

@Api("采购单服务")
@RestController
@RequestMapping(value = "{tenant}/purchase-order", produces = "application/json;charset=utf-8")
public class PurchaseOrderController {

  @ApiOperation(value = "创建采购单")
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String create(@ApiParam(required = true) @PathVariable("tenant") String tenant,
                       @ApiParam(required = true, value = "模块") @RequestBody PurchaseOrder purchaseOrder,
                       @ApiParam(required = true, value = "操作上下文") @RequestParam("operator") String operator) {

    return null;
  }

  @ApiOperation(value = "提交采购单")
  @RequestMapping(value = "/submit", method = RequestMethod.POST)
  public String submit(@ApiParam(required = true, value = "采购单ID") @RequestParam("purchaseOrderId") String purchaseOrderId,
                       @ApiParam(required = true, value = "操作上下文") @RequestParam("operator") String operator) {

    return null;
  }

  @ApiOperation(value = "审核通过")
  @RequestMapping(value = "/accepted", method = RequestMethod.POST)
  public String accepted(@ApiParam(required = true, value = "采购单ID") @RequestParam("purchaseOrderId") String purchaseOrderId,
                         @ApiParam(required = true, value = "操作上下文") @RequestParam("operator") String operator) {

    return null;
  }

  @ApiOperation(value = "审核驳回")
  @RequestMapping(value = "/rejected", method = RequestMethod.POST)
  public String rejected(@ApiParam(required = true, value = "采购单ID") @RequestParam("purchaseOrderId") String purchaseOrderId,
                         @ApiParam(required = true, value = "操作上下文") @RequestParam("operator") String operator) {

    return null;
  }

  @ApiOperation(value = "查询待审核")
  @RequestMapping(value = "/query-for-approve", method = RequestMethod.POST)
  public String queryForApproving(@ApiParam(required = true, value = "操作人") @RequestParam("operator") String operator) {

    return null;
  }


}
