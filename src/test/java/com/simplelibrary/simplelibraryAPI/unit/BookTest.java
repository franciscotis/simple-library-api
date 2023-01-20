package com.simplelibrary.simplelibraryAPI.unit;

import com.simplelibrary.simplelibraryAPI.factory.BookFakerFactory;
import com.simplelibrary.simplelibraryAPI.repository.BookRepository;
import com.simplelibrary.simplelibraryAPI.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;
import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private BookService service;

    private BookFakerFactory bookFakerFactory;

    public BookTest() {
        this.bookFakerFactory = new BookFakerFactory();
    }

    @Test
    public void itShouldCreateABookSuccessfully(){
        var book = bookFakerFactory.createBookModel();
        repository.save(book);

        var bookFinder = repository.findBookByisbn(book.getIsbn());

        Assertions.assertThat(book).isEqualTo(bookFinder);
    }

    @Test
    public void itShouldFillTheRatingValueFromExistingBook() throws IOException {
        var book = bookFakerFactory.createBookModel();
        book.setTitle("Harry Potter and the Philosopher's Stone");

        var bookCreated = service.store(bookFakerFactory.createBook(book));

        assertThat(bookCreated.getRate()).isNotNull()
                .isNotZero()
                .isGreaterThan(Double.valueOf(0));

    }

    @Test
    public void itShouldNotFillTheRatingValueFromNonexistentBook() throws IOException {
        var book = bookFakerFactory.createBookModel();
        book.setTitle("blablablablablabla");

        var bookCreated = service.store(bookFakerFactory.createBook(book));

        assertThat(bookCreated.getRate()).isZero();

    }



}
