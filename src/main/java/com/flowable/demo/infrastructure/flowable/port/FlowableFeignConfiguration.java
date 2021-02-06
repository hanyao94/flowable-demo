/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	tms-service
 * 文件名：	TmsJiraeginConfiguration.java
 * 模块说明：
 * 修改历史：
 * 2020/1/14 - seven - 创建。
 */
package com.flowable.demo.infrastructure.flowable.port;

import com.flowable.demo.infrastructure.flowable.infrastructure.fegin.AbstractBasicAuthFeginConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author seven
 */
@Configuration
public class FlowableFeignConfiguration extends AbstractBasicAuthFeginConfiguration {
  @Override
  protected String getUsername() {
    return "flowable-service.rest.username";
  }

  @Override
  protected String getPassword() {
    return "flowable-service.rest.password";
  }
}
