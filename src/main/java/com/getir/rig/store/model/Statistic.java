package com.getir.rig.store.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Statistic {

    private long totalOrderCount;
    private long totalPurchasedBooks;
    private double totalPriceOfPurchasedBooks;
}
