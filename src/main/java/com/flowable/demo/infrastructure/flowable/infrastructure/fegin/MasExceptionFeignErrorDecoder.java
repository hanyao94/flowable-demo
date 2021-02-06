/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * 
 * 项目名：	mas-bff-starter
 * 文件名：	MasExceptionFeignErrorDecoder.java
 * 模块说明：	
 * 修改历史：
 * 2019年9月7日 - __Silent - 创建。
 */
package com.flowable.demo.infrastructure.flowable.infrastructure.fegin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * MasException的异常解析器
 * 
 * 是否需要对外的异常统一格式
 * 
 * @author __Silent
 *
 */
@Component
@Slf4j
public class MasExceptionFeignErrorDecoder implements feign.codec.ErrorDecoder {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Exception decode(String methodKey, Response response) {
    try {
      String json = "{}";
      if (response.body() == null) {
        return feign.FeignException.errorStatus(methodKey, response);
      }
      if (response.body() != null) {
        json = new BufferedReader(new InputStreamReader(response.body().asInputStream())).lines()
            .parallel().collect(Collectors.joining(System.lineSeparator()));
        log.error("访问HTTP服务出现错误:{}", json);
      }
      ResponseErrorMsg responseErrorMsg = objectMapper.readValue(json, ResponseErrorMsg.class);
      if (responseErrorMsg != null) {
        if (responseErrorMsg.getException().indexOf(Exception.class.getSimpleName()) > 0) {
          return new Exception(responseErrorMsg.getMessage());
        } else if (responseErrorMsg.getException()
            .indexOf(IllegalArgumentException.class.getSimpleName()) > 0) {
          return new IllegalArgumentException(responseErrorMsg.getMessage());
        } else if (responseErrorMsg.getException()
            .indexOf(RuntimeException.class.getSimpleName()) > 0) {
          return new RuntimeException(responseErrorMsg.getMessage());
        }
      }
    } catch (IOException e) {
      log.error("", e);
    }

    if (response.status() >= 400 && response.status() <= 499) {
      return new HystrixBadRequestException("http权限问题或资源不存在,错误代码:" + response.status());
    }
    return feign.FeignException.errorStatus(methodKey, response);
  }

  @Setter
  @Getter
  public static class ResponseErrorMsg {
    private String timestamp;
    private int status;
    private String error;
    private String exception;
    private String message;
    private String path;
  }
}
