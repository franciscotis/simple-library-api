package com.simplelibrary.simplelibraryAPI.model;

import com.simplelibrary.simplelibraryAPI.dto.BookRequestDTO;
import com.simplelibrary.simplelibraryAPI.dto.BookResponseDTO;
import jakarta.persistence.*;
import lombok.*;
@Table(name = "books")
@Entity(name = "Book")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Integer pages;
    private String isbn;
    private Double rate;
    private Boolean finished;

    public Book(BookRequestDTO bookRequest){
        this.title = bookRequest.title();
        this.author = bookRequest.author();
        this.pages = bookRequest.pages();
        this.isbn = bookRequest.isbn();
        this.rate = bookRequest.rate();
        this.finished = bookRequest.finished();
    }

    public void update(BookRequestDTO bookRequest){
        this.title = bookRequest.title();
        this.author = bookRequest.author();
        this.pages = bookRequest.pages();
        this.isbn = bookRequest.isbn();
        this.rate = bookRequest.rate();
        this.finished = bookRequest.finished();
    }


}
