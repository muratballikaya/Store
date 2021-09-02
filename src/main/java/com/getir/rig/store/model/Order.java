package com.getir.rig.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    private String id;
    private Book book;
    private String userName;
    private Date orderDate;
    private long amount;
    private Double totalPrice;

    public Order(String userName){
        this.userName = userName;
    }

}
