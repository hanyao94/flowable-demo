/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	MyService.java
 * 模块说明：
 * 修改历史：
 * 2021/2/1 - seven - 创建。
 */
package com.seven.flowable.demo.domain.service;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author seven
 */
@Service
public class MyService {

  @Autowired
  private RuntimeService runtimeService;

  @Autowired
  private TaskService taskService;

  @Transactional
  public void startProcess() {
    runtimeService.startProcessInstanceByKey("oneTaskProcess");
  }

  @Transactional
  public List<Task> getTasks(String assignee) {
    return taskService.createTaskQuery().taskAssignee(assignee).list();
  }

}
