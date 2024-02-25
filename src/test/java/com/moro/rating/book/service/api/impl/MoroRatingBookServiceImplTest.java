package com.moro.rating.book.service.api.impl;

import com.moro.rating.book.client.service.BookClientService;
import com.moro.rating.book.repository.entity.BookReviewEntity;
import com.moro.rating.book.repository.service.BookReviewRepositoryService;
import com.moro.rating.book.service.api.MoroRatingBookService;
import com.moro.rating.book.service.model.Author;
import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.BookDetails;
import com.moro.rating.book.service.model.BookReview;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MoroRatingBookServiceImplTest {

    @Autowired
    private MoroRatingBookService moroRatingBookService;

    @MockBean
    private BookClientService bookClientService;

    @MockBean
    private BookReviewRepositoryService bookReviewRepositoryService;

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
                .withLanguages(null)
                .withDownloadCount(100)
                .withAuthors(List.of(author))
                .build();

        when(bookClientService.searchBooks(anyString())).thenReturn(List.of(book));

        List<Book> books = moroRatingBookService.searchBook("Frankenstein");

        verify(bookClientService, times(1)).searchBooks("Frankenstein");
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
    }

    @Test
    public void reviewBookVerifyCallsTriggered() {
        BookReview bookReview = new BookReview.Builder()
                .withBookId(84)
                .withReview("Review1")
                .withRate(4)
                .build();

        moroRatingBookService.reviewBook(bookReview);

        ArgumentCaptor<BookReviewEntity> argumentCaptor = ArgumentCaptor.forClass(BookReviewEntity.class);

        verify(bookReviewRepositoryService, times(1)).save(argumentCaptor.capture());

        BookReviewEntity reviewEntity = argumentCaptor.getValue();
        assertEquals("Review1", reviewEntity.getReview());
        assertEquals(4, reviewEntity.getRate());
        assertEquals(84, reviewEntity.getBookId());
    }

    @Test
    public void getBookVerifyCallsTriggered() {
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

        BookReview bookReview1 = new BookReview.Builder()
                .withBookId(84)
                .withReview("Review1")
                .withRate(4)
                .build();

        BookReview bookReview2 = new BookReview.Builder()
                .withBookId(84)
                .withReview("Review2")
                .withRate(2)
                .build();

        when(bookClientService.getBook(anyInt())).thenReturn(book);
        when(bookReviewRepositoryService.findBookReviewsByBookId(anyInt())).thenReturn(List.of(bookReview1, bookReview2));

        BookDetails bookDetails = moroRatingBookService.getBook(84);

        verify(bookClientService, times(1)).getBook(84);
        verify(bookReviewRepositoryService, times(1)).findBookReviewsByBookId(84);

        assertEquals(84, bookDetails.getId());
        assertEquals("Frankenstein", bookDetails.getTitle());
        assertEquals(100, bookDetails.getDownloadCount());
        assertEquals(1, bookDetails.getAuthors().size());
        assertEquals("Shelley", bookDetails.getAuthors().get(0).getName());
        assertEquals("1797", bookDetails.getAuthors().get(0).getBirthYear());
        assertEquals("1897", bookDetails.getAuthors().get(0).getDeathYear());
        assertEquals(1, bookDetails.getLanguages().size());
        assertEquals("en", bookDetails.getLanguages().get(0));
        assertEquals(3.0, bookDetails.getRating());
        assertEquals(2, bookDetails.getReviews().size());
        assertEquals("Review1", bookDetails.getReviews().get(0));
        assertEquals("Review2", bookDetails.getReviews().get(1));
    }
}