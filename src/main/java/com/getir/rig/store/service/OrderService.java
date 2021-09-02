package com.getir.rig.store.service;

import com.getir.rig.store.Util.DateUtil;
import com.getir.rig.store.dto.OrderDto;
import com.getir.rig.store.exception.NoSuchBookException;
import com.getir.rig.store.exception.NotEnoughBookException;
import com.getir.rig.store.model.Book;
import com.getir.rig.store.model.Order;
import com.getir.rig.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BookService bookService;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Order newOrder(Order order){
        // Calculate price
        order.setTotalPrice(order.getBook().getPrice()*order.getAmount());
        // valdate if stock is enough
        if(order.getAmount()>order.getBook().getAmount()){
            throw new NotEnoughBookException();
        }
        bookService.updateStock(order.getBook().getId(),-1l*order.getAmount());
        return orderRepository.save(order);
    }

    public Order convertToEntity(OrderDto orderDto){
        Optional<Book> b= bookService.findBookById(orderDto.getBookId());
        if(b.isEmpty()){
            throw new NoSuchBookException();
        }

        return Order.builder().userName(orderDto.getUserName()).book(b.get()).
                orderDate(new Date(System.currentTimeMillis())).amount(orderDto.getAmount()).build();

    }

    public List<Order> listAllOrders( Date from, Date to){
        from= DateUtil.calculateDateFrom(from);
        to = DateUtil.calculateDateTo(to);
        return orderRepository.findAllByDate(from,to);
    }

    public List<Order> listOrderByCustomer(int page, int size, String customerId){
        Pageable pageable = PageRequest.of(page,size);
         Example<Order> example = Example.of(new Order(customerId));
        return  orderRepository.findAll (example,pageable).stream().collect(Collectors.toList());

    }

}
