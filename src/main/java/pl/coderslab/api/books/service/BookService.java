package pl.coderslab.api.books.service;

import java.util.List;
import pl.coderslab.api.books.entity.Book;

public interface BookService {
    List<Book> getAll();
    void addBook(Book book);
    Book getBook(Long id);
    void updateBook(Long bookId, Book book);
    void deleteBook(Long id);
}
