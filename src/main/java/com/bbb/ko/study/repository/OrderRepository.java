package com.bbb.ko.study.repository;

import com.bbb.ko.study.entity.PurchaseOrder;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<PurchaseOrder, Long> {

    @Query("select id from purchase_order")
    List<Integer> findAllIds();

    @Query("select count(*) from order_item")
    int countItems();
}
