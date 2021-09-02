package com.getir.rig.store.it;

import com.getir.rig.store.model.Book;
import com.getir.rig.store.repository.BookRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookRepositoryIT extends  BaseRepositoryIT{

    @Autowired
    BookRepository repository;

    @AfterEach
    void cleanUp() {
        this.repository.deleteAll();
    }

    @Test
    void shouldReturnListOfBooksWithMatchingRate() {
        this.repository.save(Book.builder().name("Book1").amount(3).price(2d).build());
        this.repository.save(Book.builder().name("Book2").amount(1).price(2d).build());

        List<Book> books = repository.findAvailableBooks();

        Assert.assertEquals(2,books.size());
    }
}
