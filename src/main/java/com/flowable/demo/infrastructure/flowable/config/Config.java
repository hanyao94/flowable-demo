/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	config.java
 * 模块说明：
 * 修改历史：
 * 2021/2/3 - seven - 创建。
 */
package com.flowable.demo.infrastructure.flowable.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flowable.rest.service.api.RestResponseFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author seven
 */
@Configuration
public class Config {

  @Bean
  @ConditionalOnMissingBean
  public RestResponseFactory getRestResponseFactory(ObjectMapper objectMapper) {
    return new RestResponseFactory(objectMapper);
  }

  @Bean
  @ConditionalOnMissingBean
  public JpaProperties jpaProperties() {
    JpaProperties jpaProperties = new JpaProperties();
    jpaProperties.setShowSql(true);
    jpaProperties.getProperties().put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    return jpaProperties;
  }

  @Bean
  @ConditionalOnMissingBean
  public HibernateProperties hibernateProperties() {
    HibernateProperties hibernateProperties = new HibernateProperties();
    hibernateProperties.setDdlAuto("update");
    return hibernateProperties;
  }

}
