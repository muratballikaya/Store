package com.getir.rig.store.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@ApiModel(value = "Book Api model documentation", description = "Model")
public class BookDto {

    @ApiModelProperty(value = "Unique id field of book object")
    private String id;

    @ApiModelProperty(value = "Name of the book")
    @Size(max = 50,message = "Name can be at most 50 characters long")
    private String name;

    @ApiModelProperty(value = "Price of the book")
    @Positive(message = "price field has to be positive")
    @NotNull
    private Double price;

    @ApiModelProperty(value = "How much of the book will be effected")
    private int amount;

}
