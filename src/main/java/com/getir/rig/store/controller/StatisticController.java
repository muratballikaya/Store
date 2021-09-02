package com.getir.rig.store.controller;

import com.getir.rig.store.model.Statistic;
import com.getir.rig.store.service.StatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/stats")
@Api(value = "Statistic Api documentation")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping
    @ApiOperation(value = "Gets monthly statistics")
    public ResponseEntity<Map<String, Statistic>> getOrderCount( ){
        return new ResponseEntity<Map<String, Statistic>>(statisticService.getStatistics(), HttpStatus.OK) ;
    }



}
