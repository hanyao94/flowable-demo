/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	ModuleService.java
 * 模块说明：
 * 修改历史：
 * 2021/2/6 - seven - 创建。
 */
package com.flowable.demo.domain.service;

import com.flowable.demo.application.controller.module.Module;
import com.flowable.demo.application.controller.module.ModuleProcessDefinition;
import com.flowable.demo.domain.dao.ModuleProcessDefinitionRepository;
import com.flowable.demo.domain.dao.ModuleRepository;
import com.flowable.demo.domain.dao.PModule;
import com.flowable.demo.domain.dao.PModuleProcessDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author seven
 */
@Service
public class ModuleService {

  @Autowired
  private ModuleRepository moduleRepository;
  @Autowired
  private ModuleProcessDefinitionRepository moduleProcessDefinitionRepository;

  public String create(Module module) {
    PModule target = new PModule();
    target.setId(module.getId());
    target.setName(module.getName());
    target.setTenant(module.getTenant());
    moduleRepository.save(target);
    return target.getId();
  }

  public String bindProcessDefinition(ModuleProcessDefinition moduleProcessDefinition) {
    PModuleProcessDefinition target = new PModuleProcessDefinition();
    BeanUtils.copyProperties(moduleProcessDefinition, target);
    PModuleProcessDefinition saveTargt = moduleProcessDefinitionRepository.save(target);
    return saveTargt.getUuid();
  }
}