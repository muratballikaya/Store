package com.getir.rig.store.repository;

import com.getir.rig.store.model.Book;
import com.getir.rig.store.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

    @Query("{'orderDate' : {$gte: ?0 , $lte: ?1}}")
     List<Order> findAllByDate(@Param("dateFrom") Date from, @Param("dateTo") Date to);

}
