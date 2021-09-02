package com.getir.rig.store.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@ApiModel(value = "StockUpdate Api model documentation", description = "Model")
public class StockUpdate {

    @ApiModelProperty(value = "Unique id field of book object")
    @NotNull(message = "book id can not be empty")
    private String id;

    @ApiModelProperty(value = "How much of the book will be effected")
    @Positive(message = "amount should be positive")
    private int amount;
}
