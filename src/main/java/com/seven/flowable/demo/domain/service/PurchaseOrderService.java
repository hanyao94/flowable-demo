/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	PurchaseOrderService.java
 * 模块说明：
 * 修改历史：
 * 2021/2/6 - seven - 创建。
 */
package com.seven.flowable.demo.domain.service;

import com.seven.flowable.demo.application.controller.purchase.PurchaseOrder;
import com.seven.flowable.demo.application.controller.purchase.PurchaseState;
import com.seven.flowable.demo.domain.dao.PPurchaseOrder;
import com.seven.flowable.demo.domain.dao.PurchaseOrderRepository;
import com.seven.flowable.services.FlowableActionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author seven
 */
@Service
public class PurchaseOrderService extends FlowableActionService<PurchaseOrder> {

  @Autowired
  private PurchaseOrderRepository purchaseOrderRepository;

  public String create(PurchaseOrder order, String operator) {
    PPurchaseOrder target = new PPurchaseOrder();
    BeanUtils.copyProperties(order, target);
    purchaseOrderRepository.save(target);
    return target.getId();
  }

  public PurchaseOrder get(String tenant, String orderId) {
    PPurchaseOrder source = purchaseOrderRepository.findPPurchaseOrderByTenantAndId(tenant, orderId);
    PurchaseOrder target = new PurchaseOrder();
    BeanUtils.copyProperties(source, target);
    return target;
  }

  @Override
  public void submit(String tenant, String orderId, String operator) {
    PPurchaseOrder update = purchaseOrderRepository.findPPurchaseOrderByTenantAndId(tenant, orderId);
    update.setState(PurchaseState.pending.getCode());
    purchaseOrderRepository.save(update);
    PurchaseOrder target = new PurchaseOrder();
    BeanUtils.copyProperties(update, target);

    super.submit(tenant, orderId, operator);
  }

  @Override
  public void accepted(String tenant, String orderId, String operator) {
    PPurchaseOrder update = purchaseOrderRepository.findPPurchaseOrderByTenantAndId(tenant, orderId);
    update.setState(PurchaseState.approved.getCode());
    purchaseOrderRepository.save(update);

    super.accepted(tenant, orderId, operator);
  }

  @Override
  public void rejected(String tenant, String orderId, String operator) {
    super.rejected(tenant, orderId, operator);
  }

  public List<PurchaseOrder> query(String tenant, String operator) {
    List<String> orderIds = queryForApproving(tenant, operator);
    if (CollectionUtils.isEmpty(orderIds)) {
      return new ArrayList<>();
    }

    List<PPurchaseOrder> orders = purchaseOrderRepository.findAllByIdIn(orderIds);
    List<PurchaseOrder> purchaseOrders = new ArrayList<>();
    for (PPurchaseOrder source : orders) {
      PurchaseOrder target = new PurchaseOrder();
      BeanUtils.copyProperties(source, target);
      purchaseOrders.add(target);
    }
    return purchaseOrders;
  }
}
