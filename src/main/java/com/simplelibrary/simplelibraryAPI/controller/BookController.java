package com.simplelibrary.simplelibraryAPI.controller;

import com.simplelibrary.simplelibraryAPI.dto.BookRequestDTO;
import com.simplelibrary.simplelibraryAPI.dto.BookResponseDTO;
import com.simplelibrary.simplelibraryAPI.service.BookService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("books")
public class BookController {

@Autowired
private BookService bookService;


@GetMapping
public ResponseEntity<Page<BookResponseDTO>> getAllBooks(@PageableDefault(size=10, sort={"title"}) Pageable pagination) throws IOException {
    var page = bookService.getAllBooks(pagination);
    return ResponseEntity.ok(page);
}

@GetMapping("/{id}")
public ResponseEntity show(@PathVariable Long id){
    var book = bookService.show(id);
    return ResponseEntity.ok(book);
}

@PostMapping
    public ResponseEntity store(@RequestBody @Valid BookRequestDTO bookRequest, UriComponentsBuilder uriBuilder) throws IOException {
        var book = bookService.store(bookRequest);
        var uri = uriBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body(book);
    }

@PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid BookRequestDTO bookRequest){
    var book = bookService.update(id, bookRequest);
    return ResponseEntity.ok(book);

}

@DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
    bookService.delete(id);
    return ResponseEntity.noContent().build();
}




}
