/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	PurchaseState.java
 * 模块说明：
 * 修改历史：
 * 2021/2/5 - seven - 创建。
 */
package com.seven.flowable.demo.application.controller.purchase;

import lombok.Getter;

/**
 * @author seven
 */
@Getter
public enum PurchaseState {
  initial("00", "初始化"),
  pending("30", "待审核"),
  approved("70", "已审核"),
  forFixing("20", "待修正");

  PurchaseState(String code, String name) {
    this.code = code;
    this.name = name;
  }

  private String code;
  private String name;

}
