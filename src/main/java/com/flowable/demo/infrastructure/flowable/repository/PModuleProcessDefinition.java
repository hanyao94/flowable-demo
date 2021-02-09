/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	ModuleProcessDefinition.java
 * 模块说明：
 * 修改历史：
 * 2021/2/5 - seven - 创建。
 */
package com.flowable.demo.infrastructure.flowable.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author seven
 */
@Entity
@Getter
@Setter
@Table(name = "t_module_process_definition")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PModuleProcessDefinition {
  @Id
  @GeneratedValue(generator="jpa-uuid")
  private String uuid;
  @Column(name = "moduleId", length = 38)
  private String moduleId;
  @Column(name = "processDefinitionKey", length = 64)
  private String processDefinitionKey;
}
