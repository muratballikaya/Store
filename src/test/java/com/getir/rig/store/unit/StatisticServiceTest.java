package com.getir.rig.store.unit;

import com.getir.rig.store.model.Book;
import com.getir.rig.store.model.Order;
import com.getir.rig.store.repository.OrderRepository;
import com.getir.rig.store.service.OrderService;
import com.getir.rig.store.service.StatisticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    StatisticService statisticService;

    @Test
    public void shouldListMonthlyOrders(){
        Calendar calendar1 =Calendar.getInstance();
        calendar1.setTime(new Date());
        calendar1.set(2021,9, 1);

        Calendar calendar2 =Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.set(2021,8, 1);

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.builder().userName("mb").orderDate(calendar1.getTime()).amount(10).
                totalPrice(10d).book(Book.builder().price(1d).build()).build());
        orderList.add(Order.builder().userName("mb").orderDate(calendar2.getTime()).amount(20).
                totalPrice(20d).book(Book.builder().price(1d).build()).build());

        Mockito.when(orderRepository.findAll()).thenReturn(orderList);


        Assertions.assertTrue( statisticService.getStatistics().containsKey("OCTOBER"));
        Assertions.assertTrue( statisticService.getStatistics().containsKey("SEPTEMBER"));
        Assertions.assertEquals( statisticService.getStatistics().get("OCTOBER").getTotalPriceOfPurchasedBooks(), 10);
        Assertions.assertEquals( statisticService.getStatistics().get("SEPTEMBER").getTotalPriceOfPurchasedBooks(), 20);


    }
}
