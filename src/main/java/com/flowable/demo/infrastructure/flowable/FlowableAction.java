/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	FlowableAction.java
 * 模块说明：
 * 修改历史：
 * 2021/2/3 - seven - 创建。
 */
package com.flowable.demo.infrastructure.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RuntimeService;

import java.util.List;

/**
 * @author seven
 */
public abstract class FlowableAction<T> {

  public void submit() {
    // TODO 启动一个流程实例，通过返回的id，使用/runtime/tasks 接口的processInstanceId = id 获取对应的任务，绑定任务executionId 和单据id
  }

  public void accepted() {

  }

  public void rejected() {

  }

  public List<T> forApproving(){
    // TODO 通过assignee 获取待审核 List<task> =>List<executionId> 得到executionId列表(唯一),建立executionId和单据id的关联表，并取到对应单据
    return null;
  }
}
