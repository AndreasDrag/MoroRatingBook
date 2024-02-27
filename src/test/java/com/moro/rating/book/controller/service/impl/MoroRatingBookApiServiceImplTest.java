package com.moro.rating.book.controller.service.impl;

import com.moro.rating.book.controller.dto.BookDetailsDto;
import com.moro.rating.book.controller.dto.BookDto;
import com.moro.rating.book.controller.dto.BookRatingPerMonthDto;
import com.moro.rating.book.controller.dto.BookReviewDto;
import com.moro.rating.book.controller.service.MoroRatingBookApiService;
import com.moro.rating.book.service.api.MoroRatingBookService;
import com.moro.rating.book.service.model.Author;
import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.BookDetails;
import com.moro.rating.book.service.model.BookRatingPerMonth;
import com.moro.rating.book.service.model.BookReview;
import com.moro.rating.book.service.model.PagedResult;
import com.moro.rating.book.service.model.RatingPerMonth;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MoroRatingBookApiServiceImplTest {

    @Autowired
    private MoroRatingBookApiService moroRatingBookApiService;

    @MockBean
    private MoroRatingBookService moroRatingBookService;

    @Test
    public void searchBookVerifyCallsTriggered() {
        Author author = new Author.Builder()
                .withName("Shelley")
                .withBirthYear("1797")
                .withDeathYear("1897")
                .build();

        Book book = new Book.Builder()
                .withId(84)
                .withTitle("Frankenstein")
                .withLanguages(List.of("en"))
                .withDownloadCount(100)
                .withAuthors(List.of(author))
                .build();

        when(moroRatingBookService.searchBook(anyString(), anyInt())).thenReturn(
                new PagedResult.Builder<List<Book>>()
                        .withData(List.of(book))
                        .withPage(0)
                        .withTotal(1)
                        .withSize(1)
                        .build());

        PagedResult<List<BookDto>> result = moroRatingBookApiService.searchBook("Frankenstein", 0);

        verify(moroRatingBookService, times(1)).searchBook("Frankenstein", 0);

        assertEquals(1, result.getData().size());
        assertEquals(84, result.getData().get(0).getId());
        assertEquals("Frankenstein", result.getData().get(0).getTitle());
        assertEquals(100, result.getData().get(0).getDownloadCount());
        assertNotNull(result.getData().get(0).getAuthors());
        assertEquals("Shelley", result.getData().get(0).getAuthors().get(0).getName());
        assertEquals("1797", result.getData().get(0).getAuthors().get(0).getBirthYear());
        assertEquals("1897", result.getData().get(0).getAuthors().get(0).getDeathYear());
        assertEquals(1, result.getData().get(0).getLanguages().size());
        assertEquals("en", result.getData().get(0).getLanguages().get(0));
        assertEquals(1, result.getTotal());
        assertEquals(1, result.getSize());
        assertEquals(0, result.getPage());
    }

    @Test
    public void reviewBookVerifyCallsTriggered() {
        BookReviewDto bookReview = new BookReviewDto.Builder()
                .withBookId(84)
                .withRate(4)
                .withReview("Review1")
                .build();

        moroRatingBookApiService.reviewBook(bookReview);

        ArgumentCaptor<BookReview> argumentCaptor = ArgumentCaptor.forClass(BookReview.class);
        verify(moroRatingBookService, times(1)).reviewBook(argumentCaptor.capture());

        BookReview review = argumentCaptor.getValue();
        assertEquals(84, review.getBookId());
        assertEquals(4, review.getRate());
        assertEquals("Review1", review.getReview());
    }

    @Test
    public void getBookVerifyCallsTriggered() {
        BookDetails bookDetails = new BookDetails.Builder()
                .withId(84)
                .withTitle("Frankenstein")
                .withLanguages(List.of("en"))
                .withDownloadCount(100)
                .withAuthors(null)
                .withReviews(List.of("A Review"))
                .withRating(4.0)
                .build();

        when(moroRatingBookService.getBook(anyInt())).thenReturn(bookDetails);

        BookDetailsDto book = moroRatingBookApiService.getBook(84);

        verify(moroRatingBookService, times(1)).getBook(84);

        assertEquals(84, book.getId());
        assertEquals("Frankenstein", book.getTitle());
        assertEquals(100, book.getDownloadCount());
        assertNull(book.getAuthors());
        assertEquals(1, book.getLanguages().size());
        assertEquals("en", book.getLanguages().get(0));
        assertEquals(1, book.getReviews().size());
        assertEquals("A Review", book.getReviews().get(0));
        assertEquals(4.0, book.getRating());
    }

    @Test
    public void getTopBooksVerifyCallsTriggered() {
        Author author = new Author.Builder()
                .withName("Shelley")
                .withBirthYear("1797")
                .withDeathYear("1897")
                .build();

        Book book = new Book.Builder()
                .withId(84)
                .withTitle("Frankenstein")
                .withLanguages(List.of("en"))
                .withDownloadCount(100)
                .withAuthors(List.of(author))
                .build();

        when(moroRatingBookService.getTopBooks(anyInt())).thenReturn(List.of(book));

        List<BookDto> books = moroRatingBookApiService.getTopBooks(84);

        verify(moroRatingBookService, times(1)).getTopBooks(84);

        assertEquals(1, books.size());
        assertEquals(84, books.get(0).getId());
        assertEquals("Frankenstein", books.get(0).getTitle());
        assertEquals(100, books.get(0).getDownloadCount());
        assertNotNull(books.get(0).getAuthors());
        assertEquals("Shelley", books.get(0).getAuthors().get(0).getName());
        assertEquals("1797", books.get(0).getAuthors().get(0).getBirthYear());
        assertEquals("1897", books.get(0).getAuthors().get(0).getDeathYear());
        assertEquals(1, books.get(0).getLanguages().size());
        assertEquals("en", books.get(0).getLanguages().get(0));
    }

    @Test
    void getBookRatingPerMonthVerifyCallsTriggerred() {
        RatingPerMonth ratingPerMonth = new RatingPerMonth.Builder()
                .withCreatedDate("01-2024")
                .withRating(4.0)
                .build();
        BookRatingPerMonth bookRatingPerMonth = new BookRatingPerMonth.Builder()
                .withId(84)
                .withRatingPerMonth(List.of(ratingPerMonth))
                .build();

        when(moroRatingBookService.getBookRatingPerMonth(anyInt())).thenReturn(bookRatingPerMonth);

        BookRatingPerMonthDto bookRatingPerMonthDto = moroRatingBookApiService.getBookRatingPerMonth(84);

        verify(moroRatingBookService, times(1)).getBookRatingPerMonth(84);

        assertEquals(84, bookRatingPerMonthDto.getId());
        assertEquals(1, bookRatingPerMonthDto.getRatingPerMonth().size());
        assertEquals(4.0, bookRatingPerMonthDto.getRatingPerMonth().get(0).getRating());
        assertEquals("01-2024", bookRatingPerMonthDto.getRatingPerMonth().get(0).getCreatedDate());
    }
}