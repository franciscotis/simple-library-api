package com.simplelibrary.simplelibraryAPI.service.impl;

import com.simplelibrary.simplelibraryAPI.dto.BookRequestDTO;
import com.simplelibrary.simplelibraryAPI.dto.BookResponseDTO;
import com.simplelibrary.simplelibraryAPI.model.Book;
import com.simplelibrary.simplelibraryAPI.repository.BookRepository;
import com.simplelibrary.simplelibraryAPI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<BookResponseDTO> getAllBooks(Pageable pagination){
        var page = bookRepository.findAll(pagination).map(BookResponseDTO::new);
        return page;
    }

    @Override
    public BookResponseDTO show(Long id) {
        return new BookResponseDTO(bookRepository.getReferenceById(id));
    }

    @Override
    public Book store(BookRequestDTO bookRequest) {
        var book = new Book(bookRequest);
        bookRepository.save(book);
        return book;
    }

    @Override
    public BookResponseDTO update(Long id, BookRequestDTO bookRequest) {
        var book = bookRepository.getReferenceById(id);
        book.update(bookRequest);
        return new BookResponseDTO(book);


    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public long count(){
        return bookRepository.count();
    }

}
