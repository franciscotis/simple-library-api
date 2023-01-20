package com.simplelibrary.simplelibraryAPI.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplelibrary.simplelibraryAPI.factory.BookFakerFactory;
import com.simplelibrary.simplelibraryAPI.model.Book;
import com.simplelibrary.simplelibraryAPI.repository.BookRepository;
import com.simplelibrary.simplelibraryAPI.service.BookService;
import org.assertj.core.api.Assertions;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository repository;

    private BookFakerFactory bookFakerFactory;

    public BookControllerTest() {
        this.bookFakerFactory = new BookFakerFactory();
    }

    @Test
    void testIfBookIsCreatedSuccessfully() throws Exception {
        var book = this.bookFakerFactory.createBook();

        var result = this.mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book)))
                .andExpect(status().isCreated())
                .andReturn();

        String headerLocation = result.getResponse().getHeader("Location");
        String content = result.getResponse().getContentAsString();
        Book bookResp = new ObjectMapper().readValue(content, Book.class);
        assertNotNull(content);
//        var uri = "/books/"+bookResp.getId().toString();
//        assertEquals(headerLocation, uri.toString());

    }

    @Test
    void testIfBookIsNotCreatedWithMissingAttributes() throws Exception{
        var book = this.bookFakerFactory.createBookIncomplete();
        this.mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    void testIfAllBooksAreListedSuccessfully() throws Exception{
        this.mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testIfSingleBookInformationIsShown() throws  Exception {
        this.mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testIfBookIsUpdatedSuccessfully() throws Exception{
        var book = this.bookFakerFactory.createBookModel();
        var bookCreated = repository.save(book);

        book.setAuthor("New Author name");

        var book2 = this.bookFakerFactory.createBook(book);

        this.mockMvc.perform(put("/books/{id}",bookCreated.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book2)))
                .andExpect(status().isOk())
                .andReturn();

        var bookUpdated = repository.getReferenceById(bookCreated.getId());

        assertThat(bookUpdated.getId()).isEqualTo(bookCreated.getId());
        assertThat(bookUpdated.getAuthor()).isNotEqualTo(bookCreated.getAuthor());


    }

    @Test
    void testIfBookIsNotUpdatedSuccessfully() throws Exception{
        var book = this.bookFakerFactory.createBookModel();
        var bookCreated = repository.save(book);


        this.mockMvc.perform(put("/books/{id}",bookCreated.getId()))
                .andExpect(status().isBadRequest())
                .andReturn();


    }

    @Test
    void testIfBookIsDeletedSuccessfully() throws Exception{
        var book = this.bookFakerFactory.createBookModel();
        var bookCreated = repository.save(book);
        this.mockMvc.perform(delete("/books/"+bookCreated.getId()))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void testIfBookIsNotDeletedSuccessfully() throws Exception{
        this.mockMvc.perform(delete("/books/999999"))
                .andExpect(status().isNotFound())
                .andReturn();

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
