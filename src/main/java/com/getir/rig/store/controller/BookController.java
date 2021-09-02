package com.getir.rig.store.controller;

import com.getir.rig.store.dto.BookDto;
import com.getir.rig.store.dto.PaginationDto;
import com.getir.rig.store.dto.StockUpdate;
import com.getir.rig.store.model.Book;
import com.getir.rig.store.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book")
@Api(value = "Book Api documentation")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    @ApiOperation(value = "get available books")
    public ResponseEntity<List<Book>> listBooks(@RequestBody @Validated PaginationDto paginationDto){
        return new ResponseEntity<List<Book>>(bookService.listBooksToOrder(paginationDto.getPage(), paginationDto.getSize()), HttpStatus.CREATED) ;
    }

    @PostMapping
    @ApiOperation(value = "New book that does not exist in the database")
    public ResponseEntity<Book> saveBook(@RequestBody @Validated BookDto bookDto){
        Book book = convertToEntity(bookDto);
        return new ResponseEntity<Book>(bookService.save(book), HttpStatus.CREATED) ;
    }

    @PutMapping
    @ApiOperation(value = "Update the amount of the book that is already in the database")
    public ResponseEntity<Book> updateBookStock(@RequestBody @Validated StockUpdate stockUpdate){
       return new ResponseEntity<Book>(bookService.updateStock(stockUpdate.getId(),stockUpdate.getAmount()), HttpStatus.OK) ;
    }

    private Book convertToEntity(BookDto bookDto) {
        return Book.builder().amount(bookDto.getAmount()).name(bookDto.getName()).price(bookDto.getPrice()).build();
    }
}
