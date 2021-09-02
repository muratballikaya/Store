package com.getir.rig.store.service;

import com.getir.rig.store.model.Book;
import com.getir.rig.store.model.Order;
import com.getir.rig.store.model.Statistic;
import com.getir.rig.store.repository.OrderRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticService {

    @Autowired
    OrderRepository orderRepository;

    public Map<String, Statistic> getStatistics(){
        List<Order> allPurchasedOrders = orderRepository.findAll();

        Map<String,Statistic> map = new HashMap<String,Statistic>();



      for(Order o:allPurchasedOrders){
          LocalDate localDate = o.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
          int month = localDate.getMonthValue();
          String monthStringValue = Month.of(month).toString();
          if(map.containsKey(monthStringValue)){
              Statistic s = map.get(monthStringValue);
              s.setTotalOrderCount(s.getTotalOrderCount()+1);
              s.setTotalPurchasedBooks(s.getTotalPurchasedBooks() + o.getAmount());
              s.setTotalPriceOfPurchasedBooks(s.getTotalPurchasedBooks() + o.getTotalPrice());
          }else{
              Statistic s = Statistic.builder().totalOrderCount(1).
                      totalPriceOfPurchasedBooks(o.getTotalPrice()).totalPurchasedBooks(o.getAmount()).build();
              map.put(monthStringValue,s);
          }
      }

      return map;
    }

}
