/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	flowable-demo
 * 文件名：	PurchaseRepository.java
 * 模块说明：
 * 修改历史：
 * 2021/2/6 - seven - 创建。
 */
package com.seven.flowable.demo.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author seven
 */
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PPurchaseOrder, String> {

  PPurchaseOrder findPPurchaseOrderByTenantAndId(String tenant, String id);

  List<PPurchaseOrder> findAllByIdIn(List<String> ids);
}
