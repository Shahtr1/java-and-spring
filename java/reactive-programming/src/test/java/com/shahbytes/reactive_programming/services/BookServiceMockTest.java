package com.shahbytes.reactive_programming.services;

import com.shahbytes.reactive_programming.exception.BookException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceMockTest {

    @Mock
    private BookInfoService bookInfoService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("Should get three books")
    void getBooksMock() {

        when(bookInfoService.getBooksInfo()).thenCallRealMethod();
        when(reviewService.getReviews(anyLong())).thenCallRealMethod();

        var books = bookService.getBooks();

        StepVerifier.create(books)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should get BookException")
    void getBooksMockOnError() {

        when(bookInfoService.getBooksInfo()).thenCallRealMethod();
        when(reviewService.getReviews(anyLong())).thenThrow(new IllegalStateException("exception using test"));

        var books = bookService.getBooks();

        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }
}