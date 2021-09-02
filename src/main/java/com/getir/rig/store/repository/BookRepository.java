package com.getir.rig.store.repository;

import com.getir.rig.store.exception.BookIsLockedException;
import com.getir.rig.store.exception.NoSuchBookException;
import com.getir.rig.store.model.Book;
import com.getir.rig.store.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {

    @Query("{'amount' :  {$gt: 0}}")
    List<Book> findAvailableBooks();

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    Optional<Book> findById(String s);

    default List<Book> findAvailableBooksByPaging(int page, int size){
        return findAvailableBooks().stream().skip(page*size).limit(size).collect(Collectors.toList());
    }

}
