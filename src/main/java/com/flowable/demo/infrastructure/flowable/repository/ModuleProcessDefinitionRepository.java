/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	ModuleProcessDefinitionRepository.java
 * 模块说明：
 * 修改历史：
 * 2021/2/6 - seven - 创建。
 */
package com.flowable.demo.infrastructure.flowable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author seven
 */
public interface ModuleProcessDefinitionRepository extends JpaRepository<PModuleProcessDefinition, String> {

  PModuleProcessDefinition findByModuleId(String moduleId);
}
