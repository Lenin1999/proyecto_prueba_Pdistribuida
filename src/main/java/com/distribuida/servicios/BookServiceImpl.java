package com.distribuida.servicios;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jdbi.v3.core.Handle;

import java.util.ArrayList;

import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService{

    @Inject private DbConfig dbConfig;
    public Book findById(Integer id) {
    Book book= new Book();
//        for (Book book: lista){
//            if (book.getId()== id){
//                return book;
//            }
//        }

        //Nuevo codigo con Base de datos
        try  {
            Handle h = dbConfig.conexion();

            book = h.createQuery("select * from Books where id =" + id).mapToBean(Book.class).one();
            h.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
         return book;
    }

    public List<Book> findAll() {
        List<Book> list = null;
        try  {
            Handle h = dbConfig.conexion();
            list = h.createQuery("select * from Books").mapToBean(Book.class).list();
            h.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Book> lista= new ArrayList<>();
    public Book insertBook(Book book){

        //lista.add(book);

        //Nuevo con base de datos
        try  {
        Handle h = dbConfig.conexion();
        h.execute("insert into public.books(isbn, author, price, title) values (?, ?, ?, ?)",book.getIsbn(), book.getAuthor(),book.getPrice(), book.getTitle());
        h.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    public void deleteBook(Integer id){
//        for (int i=0;i<=lista.size();i++){
//            if (lista.get(i).getId() == id){
//               lista.remove(i);
//            }
//        }
        //Nuevo con base de datos
        try  {
            Handle h = dbConfig.conexion();
            h.execute("delete from public.books where id="+id);

            h.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
//    private int getPosicion(Book book){
//       for (int i=0; i<lista.size();i++){
//            if (lista.get(i).getId()==book.getId()){
//                return i;
//            }
//        }
//       return -1;
//    }

    public Book updateBook(Book book) {
//        int p = getPosicion(book);
//        try {
//            lista.set(p, book);
//        } catch (IndexOutOfBoundsException ioobe) {
//            return null;
//        }
//
//        return book;

        // Nuevo codigo con base de datos

        try  {
            Handle h = dbConfig.conexion();
            h.createUpdate("update books set isbn = :isbn, title = :title, author = :author, price = :price where id = :id")
                    .bind("isbn", book.getIsbn())
                    .bind("title", book.getTitle())
                    .bind("author", book.getAuthor())
                    .bind("price", book.getPrice())
                    .bind("id",book.getId())
                    .execute();
            h.close();
        }
       catch (Exception e) {
            e.printStackTrace();
        }
 return book;
    }
}
