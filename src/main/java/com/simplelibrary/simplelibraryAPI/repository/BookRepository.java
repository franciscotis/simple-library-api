package com.simplelibrary.simplelibraryAPI.repository;

import com.simplelibrary.simplelibraryAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    public Book findBookByisbn(String isbn);
}
