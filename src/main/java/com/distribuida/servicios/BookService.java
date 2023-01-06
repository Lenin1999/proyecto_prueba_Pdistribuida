package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;

public interface BookService {
   Book findById(Integer id);
    List <Book> findAll();

    Book insertBook(Book book);

    void deleteBook(Integer id);

    Book updateBook(Book book);


}
