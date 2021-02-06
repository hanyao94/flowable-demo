/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * 
 * 项目名：	mas-web-service
 * 文件名：	BaseFeginConfiguration.java
 * 模块说明：	
 * 修改历史：
 * 2019年9月7日 - __Silent - 创建。
 */
package com.flowable.demo.infrastructure.flowable.infrastructure.fegin;

import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @author __Silent
 *
 */
public abstract class AbstractBasicAuthFeginConfiguration extends FeginConfiguration {

  @Autowired
  private Environment env;

  public static int connectTimeOutMillis = 120000;// 超时时间
  public static int readTimeOutMillis = 120000;

  @Bean
  public Request.Options options() {
    return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
  }

  @Bean
  public BasicAuthRequestInterceptor basicAuthorizationInterceptor() {
    String user = env.getProperty(getUsername());
    String password = env.getProperty(getPassword());
    return new BasicAuthRequestInterceptor(user, password);
  }

  protected abstract String getUsername();

  protected abstract String getPassword();
}
