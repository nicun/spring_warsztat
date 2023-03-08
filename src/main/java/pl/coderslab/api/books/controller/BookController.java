package pl.coderslab.api.books.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.api.books.entity.Book;
import pl.coderslab.api.books.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/helloBook")
    @ResponseStatus(HttpStatus.OK)
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
            "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable Long bookId) {
        return Optional.ofNullable(bookService.getBook(bookId))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long bookId, @RequestBody Book book){
        bookService.updateBook(bookId, book);
    }

}
