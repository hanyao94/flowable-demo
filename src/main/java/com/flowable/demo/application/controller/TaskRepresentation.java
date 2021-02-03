/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TaskRepresentation.java
 * 模块说明：
 * 修改历史：
 * 2021/2/1 - seven - 创建。
 */
package com.flowable.demo.application.controller;

/**
 * @author seven
 */
public class TaskRepresentation {
  private String id;
  private String name;

  public TaskRepresentation(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}
