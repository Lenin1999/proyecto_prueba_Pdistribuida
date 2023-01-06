package com.distribuida.rest;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.GET;

import jakarta.ws.rs.core.MediaType;


import java.util.List;


@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject private BookService bookService;
    @Inject private DbConfig dbConfig;


   @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findBy(@PathParam("id") Integer id){
       dbConfig.test();
        return bookService.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll(){
       return bookService.findAll();
    }
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
    public Book insertBook(Book book) {
      return bookService.insertBook(book);
    }
    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") Integer id){
        bookService.deleteBook(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book updateBook(@PathParam("id") Integer id, Book book){
      book.setId(id);
      return bookService.updateBook(book);
    }

    /**
     * *GET /books/{id} buscar 1
     * GET /books listar
     * POST /books  insertar
     * PUT/PATCH /books/ñ{id} actualizar
     * DELETE /books/{id}
     * META-INF/microprofile-config.properties
     */

}
