package com.flowable.demo.infrastructure.flowable.infrastructure.fegin;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class ApiRequestInterceptor implements RequestInterceptor {

  private static final String TRACE_ID = "trace_id";

  @Override
  public void apply(RequestTemplate template) {
    Map<String, Collection<String>> headers = new HashMap<>();
    for (String key : template.headers().keySet()) {
      headers.put(key, template.headers().get(key));
    }
    if (MDC.get(TRACE_ID) == null) {
      MDC.put(TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
    }
    headers.put(TRACE_ID, Arrays.asList(MDC.get(TRACE_ID)));
    template.headers(headers);
  }
}
