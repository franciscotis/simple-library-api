package com.simplelibrary.simplelibraryAPI.factory;

import com.github.javafaker.Faker;
import com.simplelibrary.simplelibraryAPI.dto.BookRequestDTO;
import com.simplelibrary.simplelibraryAPI.model.Book;

public class BookFakerFactory {

    private Faker faker;

    public BookFakerFactory(){
        this.faker = new Faker();
    }

    public BookRequestDTO createBook(){
        var book = new BookRequestDTO(faker.book().title(), faker.book().author(),
                faker.number().randomDigit(), faker.number().digits(13), faker.number().randomDouble(1,1,10),
                faker.random().nextBoolean());

        return book;
    }

    public BookRequestDTO createBook(Book bookCreated){
        var book = new BookRequestDTO(bookCreated);
        return book;
    }

    public Book createBookModel(){
        var bookDTO = new BookRequestDTO(faker.book().title(), faker.book().author(),
                faker.number().randomDigit(), faker.number().digits(13), faker.number().randomDouble(1,1,10),
                faker.random().nextBoolean());

        var book = new Book(bookDTO);
        return book;
    }



    public BookRequestDTO createBookIncomplete(){
        var book = new BookRequestDTO(faker.book().title(), "",
                faker.number().randomDigit(), faker.number().digits(13), faker.number().randomDouble(1,1,10),
                faker.random().nextBoolean());

        return book;
    }

}
