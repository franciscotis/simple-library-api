package com.simplelibrary.simplelibraryAPI.db;

import com.github.javafaker.Faker;
import com.simplelibrary.simplelibraryAPI.dto.BookRequestDTO;
import com.simplelibrary.simplelibraryAPI.model.Book;
import com.simplelibrary.simplelibraryAPI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookSeeder implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        loadBookData();
    }

    private void loadBookData(){
        Faker faker = new Faker();
        if(bookService.count() == 0){
            for(int i = 0; i < 30; i++){
                BookRequestDTO book = new BookRequestDTO(faker.book().title(), faker.book().author(),
                        faker.number().randomDigit(), faker.number().digits(13), faker.number().randomDouble(1,1,10),
                        faker.random().nextBoolean());
                bookService.store(book);
            }
        }
    }
}
