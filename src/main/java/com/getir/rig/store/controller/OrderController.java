package com.getir.rig.store.controller;

import com.getir.rig.store.dto.BookDto;
import com.getir.rig.store.dto.DateRangeDto;
import com.getir.rig.store.dto.OrderDto;
import com.getir.rig.store.dto.PaginationDto;
import com.getir.rig.store.model.Book;
import com.getir.rig.store.model.Order;
import com.getir.rig.store.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
@Api(value = "Order Api documentation")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orderByCustomer/{username}")
    @ApiOperation(value = "List the orders according to pagination rule")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@RequestParam("username") String username,@RequestBody @Validated PaginationDto paginationDto){
        return new ResponseEntity<List<Order>>(orderService.listOrderByCustomer(paginationDto.getPage(),paginationDto.getSize(),username), HttpStatus.OK) ;
    }

    @GetMapping("/allOrders")
    @ApiOperation(value = "List the orders according to pagination rule")
    public ResponseEntity<List<Order>> getOrdersByDate(@RequestBody @Validated DateRangeDto dateRangeDto){
        return new ResponseEntity<List<Order>>(orderService.listAllOrders(dateRangeDto.getFrom(),dateRangeDto.getTo()), HttpStatus.OK) ;
    }

    @PostMapping
    @ApiOperation(value = "New order from customer")
    public ResponseEntity<Order> newOrder(@RequestBody @Validated OrderDto orderDto){
        Order order = orderService.convertToEntity(orderDto);
        return new ResponseEntity<Order>(orderService.newOrder(order), HttpStatus.CREATED) ;
    }

}
