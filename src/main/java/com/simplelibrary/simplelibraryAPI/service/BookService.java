package com.simplelibrary.simplelibraryAPI.service;

import com.simplelibrary.simplelibraryAPI.dto.BookRequestDTO;
import com.simplelibrary.simplelibraryAPI.dto.BookResponseDTO;
import com.simplelibrary.simplelibraryAPI.model.Book;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookService {
    public Page<BookResponseDTO> getAllBooks(Pageable pagination);
    public BookResponseDTO show(Long id);

    public Book store(BookRequestDTO book);

    public BookResponseDTO update(Long id, BookRequestDTO book);

    public void delete(Long id);

    public long count();
}


