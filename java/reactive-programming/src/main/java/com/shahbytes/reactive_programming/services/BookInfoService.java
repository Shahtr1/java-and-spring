package com.shahbytes.reactive_programming.services;

import com.shahbytes.reactive_programming.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getBooksInfo() {
        var books = List.of(
                new BookInfo(1, "Book One", "Author One", "121212"),
                new BookInfo(2, "Book Two", "Author Two", "131313"),
                new BookInfo(3, "Book Three", "Author Three", "141414")
        );

        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookInfoById(long bookId) {
        var book = new BookInfo(bookId, "Book One", "Author One", "121212");

        return Mono.just(book);
    }
}
