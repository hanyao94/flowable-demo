/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	neo-service-console
 * 文件名：	CoreFeginConfiguration.java
 * 模块说明：	
 * 修改历史：
 * 2018年10月13日 - Silent - 创建。
 */
package com.flowable.demo.infrastructure.flowable.infrastructure.fegin;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.format.Formatter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Silent
 *
 */
public abstract class FeginConfiguration {

  @Bean
  public FeignFormatterRegistrar localDateFeignFormatterRegistrar() {
    return formatterRegistry -> formatterRegistry.addFormatter(new Formatter<Date>() {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

      @Override
      public Date parse(String text, Locale locale) throws ParseException {
        return formatter.parse(text);
      }

      @Override
      public String print(Date object, Locale locale) {
        return formatter.format(object);
      }
    });
  }

  @Bean
  public Decoder feignDecoder() {
    HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(
        customObjectMapper());
    ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(
        jacksonConverter);
    return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
  }

  private ObjectMapper customObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    return objectMapper;
  }
}