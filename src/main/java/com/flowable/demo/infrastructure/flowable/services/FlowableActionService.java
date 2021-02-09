/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	FlowableAction.java
 * 模块说明：
 * 修改历史：
 * 2021/2/3 - seven - 创建。
 */
package com.flowable.demo.infrastructure.flowable.services;

import com.flowable.demo.infrastructure.flowable.entity.Approve;
import com.flowable.demo.infrastructure.flowable.port.process.ProcessInstanceCollectionClient;
import com.flowable.demo.infrastructure.flowable.port.task.TaskClient;
import com.flowable.demo.infrastructure.flowable.port.task.TaskCollectionClient;
import com.flowable.demo.infrastructure.flowable.port.task.TaskQueryClient;
import com.flowable.demo.infrastructure.flowable.repository.ModuleProcessDefinitionRepository;
import com.flowable.demo.infrastructure.flowable.repository.OrderTaskRelationRepository;
import com.flowable.demo.infrastructure.flowable.repository.PModuleProcessDefinition;
import com.flowable.demo.infrastructure.flowable.repository.POrderTaskRelation;
import org.aspectj.weaver.ResolvableTypeList;
import org.flowable.common.rest.api.DataResponse;
import org.flowable.rest.service.api.engine.variable.RestVariable;
import org.flowable.rest.service.api.runtime.process.ProcessInstanceCreateRequest;
import org.flowable.rest.service.api.runtime.process.ProcessInstanceResponse;
import org.flowable.rest.service.api.runtime.task.TaskActionRequest;
import org.flowable.rest.service.api.runtime.task.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author seven
 */
public abstract class FlowableActionService<T> {

  @Autowired
  private HttpServletRequest httpServletRequest;
  @Autowired
  private HttpServletResponse httpServletResponse;
  @Autowired
  private OrderTaskRelationRepository orderTaskRelationRepository;
  @Autowired
  private ModuleProcessDefinitionRepository moduleProcessDefinitionRepository;
  @Autowired
  private ProcessInstanceCollectionClient processInstanceCollectionClient;
  @Autowired
  private TaskCollectionClient taskCollectionClient;
  @Autowired
  private TaskQueryClient taskQueryClient;
  @Autowired
  private TaskClient taskClient;

  public void submit(String tenant, String orderId, String operator) {
    // 根据模块绑定的流程定义，启动一个流程实例，通过返回的id，使用/runtime/tasks 接口的processInstanceId = id 获取对应的任务，绑定任务executionId 和单据id

    // 获取子类泛型类名
    ResolvableType resolvableType = ResolvableType.forClass(this.getClass()).getSuperType();
    ResolvableType[] types = resolvableType.getGenerics();
    Class<T> TClass = (Class<T>) types[0].resolve();

    // 通过方法返回值获取模块ID，模块ID与流程定义Key值进行绑定
    PModuleProcessDefinition moduleProcessDefinition = moduleProcessDefinitionRepository.findByModuleId(TClass.getSimpleName());

    // 启动流程
    ProcessInstanceCreateRequest request = new ProcessInstanceCreateRequest();
    request.setProcessDefinitionKey(moduleProcessDefinition.getProcessDefinitionKey());
    ProcessInstanceResponse processInstance = processInstanceCollectionClient.createProcessInstance(request, httpServletRequest, httpServletResponse);

    // 获取流程实例对应的任务task
    Map<String, String> requestParams = new HashMap<>();
    requestParams.put("processInstanceId", processInstance.getId());
    DataResponse<TaskResponse> taskResponse = taskCollectionClient.getTasks(requestParams, httpServletRequest);
    if (CollectionUtils.isEmpty(taskResponse.getData())) {
      return;
    }

    // 拿到任务task对应的executeId(在运行过程中唯一) 绑定单据Id
    String taskExecutionId = taskResponse.getData().get(0).getExecutionId();
    POrderTaskRelation taskRelation = new POrderTaskRelation();
    taskRelation.setOrderId(orderId);
    taskRelation.setExecutionId(taskExecutionId);
    orderTaskRelationRepository.save(taskRelation);
  }

  public void accepted(String tenant, String orderId, String operator) {
    POrderTaskRelation orderTaskRelation = orderTaskRelationRepository.findByOrderId(orderId);
    if (orderTaskRelation == null) {
      return;
    }

    Map<String, String> requestParams = new HashMap<>();
    requestParams.put("executionId", orderTaskRelation.getExecutionId());
    DataResponse<TaskResponse> taskResponse = taskCollectionClient.getTasks(requestParams, httpServletRequest);
    if (CollectionUtils.isEmpty(taskResponse.getData())) {
      return;
    }

    TaskActionRequest taskActionRequest = new TaskActionRequest();
    taskActionRequest.setAction(TaskActionRequest.ACTION_COMPLETE);
    RestVariable variable = new RestVariable();
    variable.setName(Approve.APPROVAL_CONDITION_PARAM);
    variable.setValue(Approve.accepted.getCode());
    variable.setType("string");
    taskActionRequest.setVariables(Arrays.asList(variable));
    String taskId = taskResponse.getData().get(0).getId();
    taskClient.executeTaskAction(taskId, taskActionRequest);
  }

  public void rejected(String tenant, String orderId, String operator) {
    POrderTaskRelation orderTaskRelation = orderTaskRelationRepository.findByOrderId(orderId);
    if (orderTaskRelation == null) {
      return;
    }

    Map<String, String> requestParams = new HashMap<>();
    requestParams.put("executionId", orderTaskRelation.getExecutionId());
    DataResponse<TaskResponse> taskResponse = taskCollectionClient.getTasks(requestParams, httpServletRequest);
    if (CollectionUtils.isEmpty(taskResponse.getData())) {
      return;
    }

    TaskActionRequest taskActionRequest = new TaskActionRequest();
    taskActionRequest.setAction(TaskActionRequest.ACTION_COMPLETE);
    RestVariable variable = new RestVariable();
    variable.setName(Approve.APPROVAL_CONDITION_PARAM);
    variable.setValue(Approve.rejected.getCode());
    variable.setType("string");
    taskActionRequest.setVariables(Arrays.asList(variable));
    String taskId = taskResponse.getData().get(0).getId();
    taskClient.executeTaskAction(taskId, taskActionRequest);
  }

  public List<String> queryForApproving(String tenant, String operator) {
    //  通过assignee 获取待审核 List<task> =>List<executionId> 得到executionId列表(唯一),建立executionId和单据id的关联表，并取到对应单据

    Map<String, String> requestParams = new HashMap<>();
    requestParams.put("assignee", operator);
    DataResponse<TaskResponse> taskResponse = taskCollectionClient.getTasks(requestParams, httpServletRequest);
    if (CollectionUtils.isEmpty(taskResponse.getData())) {
      return new ArrayList<>();
    }
    List<String> executionIds = taskResponse.getData().stream().map(TaskResponse::getExecutionId).collect(Collectors.toList());
    List<POrderTaskRelation> orderTaskRelations = orderTaskRelationRepository.findByExecutionIdIn(executionIds);

    return orderTaskRelations.stream().map(POrderTaskRelation::getOrderId).collect(Collectors.toList());
  }
}
