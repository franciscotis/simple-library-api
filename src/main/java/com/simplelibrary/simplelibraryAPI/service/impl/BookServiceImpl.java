package com.simplelibrary.simplelibraryAPI.service.impl;

import com.simplelibrary.simplelibraryAPI.dto.BookRequestDTO;
import com.simplelibrary.simplelibraryAPI.dto.BookResponseDTO;
import com.simplelibrary.simplelibraryAPI.model.Book;
import com.simplelibrary.simplelibraryAPI.repository.BookRepository;
import com.simplelibrary.simplelibraryAPI.service.BookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    public Book store(BookRequestDTO bookRequest) throws IOException {
        var book = new Book(bookRequest);
        book.setRate(this.scrapRatings(book.getTitle()));
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

    private Double scrapRatings(String title) {
        Double rating = Double.valueOf(0);
        try{
            String titleEdited = title.replace(" ","-");
            Document doc = Jsoup.connect("https://www.skoob.com.br/"+titleEdited).get();
            Elements info = doc.select( "div#resultadoBusca")
                    .select("div.box_lista_busca_vertical")
                    .select("div.detalhes")
                    .select("a");
            Element firstBook = info.first();
            if(firstBook != null){
                Document book = Jsoup.connect("https://www.skoob.com.br/"+firstBook.attr("href")).get();
                Elements ratingElement = book.select("div#pg-livro-box-rating")
                        .select("span.rating");
                rating = Double.parseDouble(ratingElement.text());
            }
        }catch (Exception e){
        }
        finally {
            return rating;
        }
    }

}
