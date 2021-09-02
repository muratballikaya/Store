package com.getir.rig.store.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@Builder
public class Book {

    @Id
    private String id;
    private String name;
    private Double price;
    private long amount;

}
