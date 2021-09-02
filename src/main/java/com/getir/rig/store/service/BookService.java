package com.getir.rig.store.service;

import com.getir.rig.store.dto.BookDto;
import com.getir.rig.store.exception.NoSuchBookException;
import com.getir.rig.store.exception.NotEnoughBookException;
import com.getir.rig.store.model.Book;
import com.getir.rig.store.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public Book save(Book book){
        return  repository.save(book);
    }

    public Book updateStock(String bookId, long amount){
          return amount>=0 ? increaseAmount(bookId,amount) : decreaseAmount(bookId,amount);
    }

    private Book decreaseAmount(String bookId,long amount) {
        Optional<Book> currentBookOpt = repository.findById(bookId);
        if(currentBookOpt.isEmpty()) throw new NoSuchBookException();

        Book book = currentBookOpt.get();

        if(book.getAmount()+ amount < 0) throw  new NotEnoughBookException();

        book.setAmount(amount + book.getAmount());

        return repository.save(book);
    }

    private Book increaseAmount(String bookId, long amount) {

        Optional<Book> currentBookOpt = repository.findById(bookId);
        if(currentBookOpt.isEmpty()) throw new NoSuchBookException();

        Book book = currentBookOpt.get();
        book.setAmount(book.getAmount() + amount);
        return repository.save(book);
    }

    /**
     *
     * @param page of the book list for pagination
     * @param size of the books per page
     * @return books that are not currently being processing...
     */
    public List<Book> listBooksToOrder(int page, int size ){
        return  repository.findAvailableBooksByPaging(page,size);
    }

    public Optional<Book> findBookById(String id){
        return repository.findById(id);
    }

}
