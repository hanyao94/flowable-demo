/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	module.java
 * 模块说明：
 * 修改历史：
 * 2021/2/5 - seven - 创建。
 */
package com.seven.flowable.demo.application.controller.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author seven
 */
@Getter
@Setter
@ApiModel("模块定义")
public class Module {
  @ApiModelProperty(value = "租户", required = true)
  private String tenant;
  @ApiModelProperty(value = "模块id", required = true)
  private String id;
  @ApiModelProperty(value = "模块名称", required = true)
  private String name;
}
