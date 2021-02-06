/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	ModuleController.java
 * 模块说明：
 * 修改历史：
 * 2021/2/5 - seven - 创建。
 */
package com.flowable.demo.application.controller.module;

import com.flowable.demo.domain.service.ModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seven
 */

@Api("模块服务")
@RestController
@RequestMapping(value = "module", produces = "application/json;charset=utf-8")
public class ModuleController {

  @Autowired
  private ModuleService moduleService;

  @ApiOperation(value = "新建模块")
  @RequestMapping(value = "{tenant}/create", method = RequestMethod.POST)
  public String create(@ApiParam(required = true) @PathVariable("tenant") String tenant,
                       @ApiParam(required = true, value = "模块") @RequestBody Module module) {

    return moduleService.create(module);
  }

  @ApiOperation(value = "模块流程绑定")
  @RequestMapping(value = "/bind", method = RequestMethod.POST)
  public String bindProcessDefinition(@ApiParam(required = true, value = "模块ID") @RequestParam("moduleId") String moduleId,
                                      @ApiParam(required = true, value = "流程key") @RequestParam("processDefinitionKey") String processDefinitionKey) {
    ModuleProcessDefinition entity = new ModuleProcessDefinition();
    entity.setModuleId(moduleId);
    entity.setProcessDefinitionKey(processDefinitionKey);
    return moduleService.bindProcessDefinition(entity);
  }

}
