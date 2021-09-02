package com.getir.rig.store.unit;

import com.getir.rig.store.model.Book;
import com.getir.rig.store.repository.BookRepository;
import com.getir.rig.store.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository repository;

    @InjectMocks
    BookService bookService;

    @BeforeEach
    public void before(){
    }

    @Test
    public void shouldSaveNewBook(){
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(Book.builder().build());
        Assertions.assertTrue(bookService.save(Book.builder().name("book").build())!=null);  ;
    }

    @Test
    public void shouldIncreaseAmount(){
        Book book = Book.builder().amount(2).build();
        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(book));
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(book);

        Book result = bookService.updateStock("",1);
        Assertions.assertEquals(result.getAmount(),3);
    }

    @Test
    public void shouldDecreaseAmount(){
        Book book = Book.builder().amount(2).build();
        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(book));
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(book);

        Book result = bookService.updateStock("",-1);
        Assertions.assertEquals(result.getAmount(),1);
    }

    @Test
    public void shouldFindBookById(){
        Book book = Book.builder().amount(2).id("1").build();
        Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(book));
        Assertions.assertEquals(bookService.findBookById("1").get().getId(),"1");
    }

}
