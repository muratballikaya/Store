package com.getir.rig.store.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@ApiModel(value = "Order Api model documentation", description = "Model")
@Builder
public class OrderDto {

    @ApiModelProperty(value = "Unique id field of order object")
    private String id;

    @ApiModelProperty(value = "Unique id field of book object")
    @NotNull
    private String bookId;

    @ApiModelProperty(value = "Username of the customer")
    @NotNull
    @Size(max = 30, message = "Username can be at most 30 characters long")
    private String userName;

    @ApiModelProperty(value = "Amount to order")
    @Positive(message = "Amount has to be a positive number")
    private int amount;

    @ApiModelProperty(value = "Order date")
    private Date orderDate;


}
