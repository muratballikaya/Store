package com.getir.rig.store.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
@ApiModel(value = "DateRange Api model documentation", description = "Model")
public class DateRangeDto {
    @ApiModelProperty(value = "For querying orders by date. Starts from. Format : yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date from;

    @ApiModelProperty(value = "For querying orders by date. Lasts to. Format : yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date to;
}
