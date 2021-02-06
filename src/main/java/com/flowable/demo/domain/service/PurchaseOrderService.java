/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	PurchaseOrderService.java
 * 模块说明：
 * 修改历史：
 * 2021/2/6 - seven - 创建。
 */
package com.flowable.demo.domain.service;

import com.flowable.demo.domain.dao.PurchaseOrderRepository;
import com.flowable.demo.infrastructure.flowable.services.FlowableActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author seven
 */
@Service
public class PurchaseOrderService extends FlowableActionService {

  @Autowired
  private PurchaseOrderRepository purchaseOrderRepository;

  @Override
  public void submit(Object order, String operator) {
    // TODO 业务
    super.submit(order,operator);
  }

  @Override
  public void accepted(String tenant, String orderId, String operator) {
    // TODO 业务
    super.accepted(tenant, orderId, operator);
  }

  @Override
  public void rejected(String tenant, String orderId, String operator) {
    // TODO 业务
    super.rejected(tenant, orderId, operator);
  }

  @Override
  public List queryForApproving(String tenant, String operator) {
    // TODO 业务
    return super.queryForApproving(tenant, operator);
  }
}
