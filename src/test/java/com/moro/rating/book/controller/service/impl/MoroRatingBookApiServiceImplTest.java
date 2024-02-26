package com.moro.rating.book.controller.service.impl;

import com.moro.rating.book.controller.dto.BookReviewDto;
import com.moro.rating.book.controller.service.MoroRatingBookApiService;
import com.moro.rating.book.service.api.MoroRatingBookService;
import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.BookDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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
        when(moroRatingBookService.searchBook(anyString())).thenReturn(List.of());
        moroRatingBookApiService.searchBook("Frankenstein");
        verify(moroRatingBookService, times(1)).searchBook("Frankenstein");
    }

    @Test
    public void reviewBookVerifyCallsTriggered() {
        moroRatingBookApiService.reviewBook(new BookReviewDto.Builder().build());
        verify(moroRatingBookService, times(1)).reviewBook(any());
    }

    @Test
    public void getBookVerifyCallsTriggered() {
        when(moroRatingBookService.getBook(anyInt())).thenReturn(new BookDetails.Builder().build());
        moroRatingBookApiService.getBook(84);
        verify(moroRatingBookService, times(1)).getBook(84);
    }

    @Test
    public void getTopBooksVerifyCallsTriggered() {
        when(moroRatingBookService.getTopBooks(anyInt())).thenReturn(List.of(new Book.Builder().build()));
        moroRatingBookApiService.getTopBooks(84);
        verify(moroRatingBookService, times(1)).getTopBooks(84);
    }
}