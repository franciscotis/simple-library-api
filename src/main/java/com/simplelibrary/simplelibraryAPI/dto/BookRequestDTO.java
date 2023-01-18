package com.simplelibrary.simplelibraryAPI.dto;

import com.simplelibrary.simplelibraryAPI.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequestDTO(
        @NotBlank
        String title,
        @NotBlank
        String author,
        @NotNull
        Integer pages,
        @NotBlank
        String isbn,
        Double rate,
        @NotNull
        Boolean finished
) {

        public BookRequestDTO(Book book){
                this(book.getTitle(), book.getAuthor(), book.getPages(), book.getIsbn(), book.getRate(), book.getFinished());
        }
}
