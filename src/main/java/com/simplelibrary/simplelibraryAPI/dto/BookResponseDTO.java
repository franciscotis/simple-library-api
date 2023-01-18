package com.simplelibrary.simplelibraryAPI.dto;

import com.simplelibrary.simplelibraryAPI.model.Book;

public record BookResponseDTO(String title, String author, Integer pages, String isbn, Double rate, Boolean finished) {
    public BookResponseDTO(Book book){
        this(book.getTitle(), book.getAuthor(), book.getPages(), book.getIsbn(), book.getRate(), book.getFinished());
    }

}
