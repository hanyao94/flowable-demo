/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	ApprovalResults.java
 * 模块说明：
 * 修改历史：
 * 2021/2/4 - seven - 创建。
 */
package com.flowable.demo.infrastructure.flowable.entity;

import lombok.Getter;

/**
 * 审批
 *
 * @author seven
 */
@Getter
public enum Approve {
  /*通过*/
  accepted("1", "accepted"),

  /*拒绝*/
  rejected("-1", "rejected");

  private String code;
  private String name;

  Approve(String code, String name) {
    this.code = code;
    this.name = name;
  }

  /*审批条件参数，工作流约定条件*/
  public static final String APPROVAL_CONDITION_PARAM = "outcome";
}
