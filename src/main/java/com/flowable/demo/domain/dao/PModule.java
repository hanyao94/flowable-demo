/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	module.java
 * 模块说明：
 * 修改历史：
 * 2021/2/5 - seven - 创建。
 */
package com.flowable.demo.domain.dao;

import lombok.Getter;
import lombok.Setter;

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
@Table(name = "t_module")
public class PModule {
  @Id
  @GeneratedValue(generator="system-uuid")
  private String uuid;
  @Column(name = "id", length = 38)
  private String id;
  @Column(name = "tenant", length = 38)
  private String tenant;
  @Column(name = "name", length = 64)
  private String name;
}
