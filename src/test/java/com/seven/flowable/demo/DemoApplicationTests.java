package com.seven.flowable.demo;

import com.seven.flowable.demo.domain.service.PurchaseOrderService;
import com.seven.flowable.port.process.ProcessInstanceCollectionClient;
import com.seven.flowable.port.task.TaskClient;
import com.seven.flowable.port.task.TaskCollectionClient;
import com.seven.flowable.port.task.TaskQueryClient;
import org.flowable.common.rest.api.DataResponse;
import org.flowable.rest.service.api.runtime.task.TaskResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@SpringBootTest
class DemoApplicationTests {

  @Autowired
  private HttpServletRequest httpServletRequest;
  @Autowired
  private HttpServletResponse httpServletResponse;
  @Autowired
  private ProcessInstanceCollectionClient processInstanceCollectionClient;
  @Autowired
  private TaskCollectionClient taskCollectionClient;
  @Autowired
  private TaskQueryClient taskQueryClient;
  @Autowired
  private TaskClient taskClient;

  @Autowired
  private PurchaseOrderService purchaseOrderService;

  @Test
  public void testProcessClient() {

  }

  @Test
  public void testProcessTask() {
    DataResponse<TaskResponse> tasks = taskCollectionClient.getTasks(new HashMap<>(), httpServletRequest);
    System.out.println(tasks.getTotal());
  }

  @Test
  public void testPurchaseOrderService() throws NoSuchMethodException {
    purchaseOrderService.submit("123", "123", "123");
  }
}
