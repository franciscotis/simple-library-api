package com.simplelibrary.simplelibraryAPI.db;

import com.simplelibrary.simplelibraryAPI.dto.BookRequestDTO;
import com.simplelibrary.simplelibraryAPI.factory.BookFakerFactory;
import com.simplelibrary.simplelibraryAPI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BookSeeder implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        loadBookData();
    }

    private void loadBookData() throws IOException {
        BookFakerFactory bookFakerFactory = new BookFakerFactory();
        if(bookService.count() == 0){
            for(int i = 0; i < 30; i++){
                BookRequestDTO book = bookFakerFactory.createBook();
                bookService.store(book);
            }
        }
    }
}
