package com.getir.rig.store.it;

import com.getir.rig.store.Util.DateUtil;
import com.getir.rig.store.model.Order;
import com.getir.rig.store.repository.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class OrderRepositoryIT extends BaseRepositoryIT {


    @Autowired
    OrderRepository repository;

    @AfterEach
    void cleanUp() {
        this.repository.deleteAll();
    }

    @Test
    void shouldReturnListOfOrdersWithDateRange() {
        this.repository.save(Order.builder().orderDate(new Date()).amount(2).userName("mb").totalPrice(10d).build());
        this.repository.save(Order.builder().orderDate(new Date()).amount(2).userName("db").totalPrice(10d).build());


        List<Order> orders = repository.findAllByDate(DateUtil.calculateDateFrom(new Date()),DateUtil.calculateDateTo(new Date()));

        Assert.assertEquals(2,orders.size());
    }
}
