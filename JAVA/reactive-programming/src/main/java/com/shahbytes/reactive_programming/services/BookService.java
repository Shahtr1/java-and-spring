package com.shahbytes.reactive_programming.services;

import com.shahbytes.reactive_programming.domain.Book;
import com.shahbytes.reactive_programming.domain.Review;
import com.shahbytes.reactive_programming.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class BookService {

    private final BookInfoService bookInfoService;
    private final ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getBooks() {
        var allBooksInfo = bookInfoService.getBooksInfo();
        return allBooksInfo
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is :{}", throwable.getMessage());
                    return new BookException("Exception occurred while fetching books");
                })
                .log();
    }

    public Mono<Book> getBookById(long bookId) {
        var bookInfo = bookInfoService.getBookInfoById(bookId);
        var review = reviewService.getReviews(bookId).collectList();

        return bookInfo.zipWith(review, Book::new);

    }
}
