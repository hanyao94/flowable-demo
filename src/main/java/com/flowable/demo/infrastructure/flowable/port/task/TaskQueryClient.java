/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	FlowableRestClient.java
 * 模块说明：
 * 修改历史：
 * 2021/2/6 - seven - 创建。
 */
package com.flowable.demo.infrastructure.flowable.port.task;

import com.flowable.demo.infrastructure.flowable.port.FlowableFeignConfiguration;
import org.flowable.rest.service.api.runtime.process.ProcessInstanceCollectionResource;
import org.flowable.rest.service.api.runtime.task.TaskQueryResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author seven
 */
@Component
@FeignClient(name = "flowable-rest-service", url = "${flowable-service.rest.url:}", decode404 = true, configuration = FlowableFeignConfiguration.class)
@RequestMapping(value = "flowable-rest/service", produces = "application/json;charset=utf-8")
public class TaskQueryClient extends TaskQueryResource {

}
