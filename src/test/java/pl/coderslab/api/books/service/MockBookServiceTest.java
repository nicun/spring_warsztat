package pl.coderslab.api.books.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.coderslab.api.books.entity.Book;

class MockBookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new MockBookService();
    }

    @Test
    void shouldGetBookFromRepository() {
        //given
        Long bookId = 2L;
        List<Object> expectingValues = Arrays.asList(bookId, "Thinking in Java", "9788324631766", "Bruce Eckel",
            "Helion", "programming");
        //when
        Book book = bookService.getBook(bookId);

        //then
        assertThat(book).extracting("id", "title", "isbn", "author", "publisher", "type")
            .isEqualTo(expectingValues);
    }

    @Test
    void shouldGetNullWhenBookDontExist() {
        //expecting
        assertThat(bookService.getBook(10L)).isNull();
    }

    @Test
    void shouldCreateAndAddANewBook() {
        //given
        Book book = Book.builder().build();

        //when
        bookService.addBook(book);

        //then
        assertThat(bookService.getAll().size()).isEqualTo(4);
    }

    @Test
    void shouldntCreateAndAddANewBookWhenObjectIsNull() {
        //given
        //when
        bookService.addBook(null);

        //then
        assertThat(bookService.getAll().size()).isEqualTo(3);
    }

    @Test
    void shouldDeleteBookFromRepository() {
        //given
        Long bookId = 1L;
        //when
        bookService.deleteBook(bookId);

        //then
        assertThat(bookService.getAll().size()).isEqualTo(2);
        assertThat(bookService.getBook(bookId)).isNull();
    }

    @Test
    void shouldUpdateBook(){
        //given
        Long bookId = 2L;
        Book book = Book.builder()
            .id(bookId)
            .author("Eckel Bruce")
            .isbn("97883246317666")
            .title("Thinking in JavaScript")
            .publisher("Hell")
            .type("romance")
            .build();

        //when
        bookService.updateBook(bookId, book);

        //then
        assertThat(bookService.getBook(bookId)).isEqualTo(book);
    }

}