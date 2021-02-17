/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	Purchase.java
 * 模块说明：
 * 修改历史：
 * 2021/2/5 - seven - 创建。
 */
package com.seven.flowable.demo.application.controller.purchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author seven
 */
@ApiModel("采购订单")
@Getter
@Setter
public class PurchaseOrder {
  @ApiModelProperty(value = "租户", required = true)
  private String tenant;
  @ApiModelProperty(value = "采购ID", required = true)
  private String id;
  @ApiModelProperty(value = "采购主题", required = true)
  private String name;
  @ApiModelProperty(value = "采购单状态", required = true)
  private String state = PurchaseState.initial.getCode();
  @ApiModelProperty(value = "采购说明", required = true)
  private String remark;
  @ApiModelProperty(value = "采购员", required = true)
  private String buyer;
}
