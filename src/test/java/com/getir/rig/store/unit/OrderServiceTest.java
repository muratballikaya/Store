package com.getir.rig.store.unit;

import com.getir.rig.store.dto.OrderDto;
import com.getir.rig.store.exception.NotEnoughBookException;
import com.getir.rig.store.model.Book;
import com.getir.rig.store.model.Order;
import com.getir.rig.store.repository.BookRepository;
import com.getir.rig.store.repository.OrderRepository;
import com.getir.rig.store.service.BookService;
import com.getir.rig.store.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository repository;

    @Mock
    BookRepository bookRepository;

    @Mock
    BookService bookService;

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    public void before(){
    }

    @Test
    public void shouldReturnRelevantTotalPrice(){
        Book book = Book.builder().id("1").name("Book1").price(10d).amount(10).build();
        Order order =  Order.builder().orderDate(new Date()).book(book).amount(1).userName("mb").build();
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(order);
        Assertions.assertEquals(orderService.newOrder(order).getTotalPrice(),10d);  ;
    }

    @Test
    public void shouldThrowNotEnoughBookException(){
        Book book = Book.builder().id("1").name("Book1").price(10d).amount(0).build();
        Order order =  Order.builder().orderDate(new Date()).book(book).amount(1).userName("mb").build();
        Assertions.assertThrows(NotEnoughBookException.class,()->orderService.newOrder(order).getTotalPrice());  ;
    }

    @Test
    public void shouldConvertToEntity(){
        Book book = Book.builder().id("1").name("Book1").price(10d).amount(0).build();
        OrderDto orderDto= OrderDto.builder().userName("mb").build();
        Mockito.when(bookService.findBookById(ArgumentMatchers.any())).thenReturn(Optional.of(book));
        Assertions.assertEquals( orderService.convertToEntity(orderDto).getUserName(),"mb");
    }

    @Test
    public void shouldListAllOrders(){
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.builder().build());
        orderList.add(Order.builder().build());
        Mockito.when(repository.findAllByDate(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(orderList);
        Assertions.assertEquals( orderService.listAllOrders(new Date(),new Date()).size(),2);
    }

    @Test
    public void shouldListAllOrdersByCustomer(){
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.builder().build());
        orderList.add(Order.builder().build());
        Page<Order> page = new PageImpl<Order>(orderList,  PageRequest.of(0,2),2);
        Mockito.when(repository.findAll(ArgumentMatchers.any(), ArgumentMatchers.any(Pageable.class))).thenReturn(page);
        Assertions.assertEquals( orderService.listOrderByCustomer(0,2,"mb").size(),2);
    }



}
