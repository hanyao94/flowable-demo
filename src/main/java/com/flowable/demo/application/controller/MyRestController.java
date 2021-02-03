/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	MyRestController.java
 * 模块说明：
 * 修改历史：
 * 2021/2/1 - seven - 创建。
 */
package com.flowable.demo.application.controller;

import com.flowable.demo.domain.service.MyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seven
 */
@Api(tags = "流程接口测试")
@RestController
public class MyRestController {

  @Autowired
  private MyService myService;

  @ApiOperation(value = "初始化流程")
  @RequestMapping(value="/process", method= RequestMethod.POST)
  public void startProcessInstance() {
    myService.startProcess();
  }

  @ApiOperation(value = "获取流程任务")
  @RequestMapping(value="/tasks", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
  public List<TaskRepresentation> getTasks( @ApiParam(required = true, value = "授予人")@RequestParam String assignee) {
    List<Task> tasks = myService.getTasks(assignee);
    List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
    for (Task task : tasks) {
      dtos.add(new TaskRepresentation(task.getId(), task.getName()));
    }
    return dtos;
  }
}