package pl.coderslab.api.books.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pl.coderslab.api.books.entity.Book;

@Service
public class MockBookService implements BookService {

    private List<Book> books;
    private Long id = 0L;

    public MockBookService() {
        books = new ArrayList<>();
        addBook(Book.builder()
            .author("Michalina Odrowska")
            .isbn("1500100900")
            .title("Sztuka smarkania")
            .publisher("Wydawnictwa Szkolne i Przedszkolne")
            .type("Poradniki")
            .build());

        addBook(Book.builder()
            .author("Bruce Eckel")
            .isbn("9788324631766")
            .title("Thinking in Java")
            .publisher("Helion")
            .type("programming")
            .build());

        addBook(Book.builder()
            .author("Tom Morello")
            .isbn("666")
            .title("Anarchia dla opornych")
            .publisher("ReadMe")
            .type("Poradniki")
            .build());
    }

    private Long next() {
        return ++id;
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public void addBook(Book book) {

        Optional.ofNullable(book).ifPresent(n ->
            books.add(Book.builder()
                .id(next())
                .author(n.getAuthor())
                .isbn(n.getIsbn())
                .title(n.getTitle())
                .publisher(n.getPublisher())
                .type(n.getType())
                .build())
        );
    }

    @Override
    public Book getBook(Long bookId) {
        return books.stream()
            .filter(n -> bookId.equals(n.getId()))
            .findAny()
            .orElse(null);
    }

    @Override
    public void updateBook(Long bookId, Book book) {
        Optional.ofNullable(book)
            .filter(n -> bookId > 0)
            .map(n -> getBook(bookId))
            .map(books::indexOf)
            .ifPresent(n -> books.set(n, book));
    }

    @Override
    public void deleteBook(Long bookId) {
        Optional.ofNullable(getBook(bookId))
            .ifPresent(books::remove);
    }
}
