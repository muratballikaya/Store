package com.getir.rig.store.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@ApiModel(value = "Pagination Api model documentation", description = "Model")
public class PaginationDto {

    @PositiveOrZero
    @ApiModelProperty(value = "Page number to show")
    int page;
    @Positive
    @ApiModelProperty(value = "Size of the page")
    int size;
}
