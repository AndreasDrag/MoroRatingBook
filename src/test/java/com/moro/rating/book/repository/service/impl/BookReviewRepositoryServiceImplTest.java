package com.moro.rating.book.repository.service.impl;

import com.moro.rating.book.repository.BookReviewRepository;
import com.moro.rating.book.repository.entity.BookReviewEntity;
import com.moro.rating.book.repository.service.BookReviewRepositoryService;
import com.moro.rating.book.service.model.BookReview;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookReviewRepositoryServiceImplTest {

    @Autowired
    private BookReviewRepositoryService bookReviewRepositoryService;

    @MockBean
    private BookReviewRepository bookReviewRepository;

    @Test
    public void findBookReviewsByBookIdWhenReviewsNotExist() {
        when(bookReviewRepository.findAllByBookId(any())).thenReturn(
                Optional.of(List.of()));
        List<BookReview> bookReviews = bookReviewRepositoryService.findBookReviewsByBookId(84);

        verify(bookReviewRepository, times(1)).findAllByBookId(84);

        assertEquals(0, bookReviews.size());
    }

    @Test
    public void findBookReviewsByBookIdWhenReviewsAreExists() {
        BookReviewEntity review1 = new BookReviewEntity();
        review1.setReview("Review1");
        review1.setBookId(84);
        review1.setRate(4);
        BookReviewEntity review2 = new BookReviewEntity();
        review1.setReview("Review2");
        review1.setBookId(84);
        review1.setRate(2);

        when(bookReviewRepository.findAllByBookId(any())).thenReturn(
                Optional.of(List.of(review1, review2)));
        List<BookReview> bookReviews = bookReviewRepositoryService.findBookReviewsByBookId(84);

        verify(bookReviewRepository, times(1)).findAllByBookId(84);

        assertEquals(2, bookReviews.size());
    }

    @Test
    public void findTopBooks() {
        when(bookReviewRepository.findTopNBooksIdsByAverageRate(any())).thenReturn(
                Optional.of(List.of(84)));

        List<Integer> topBookIds = bookReviewRepositoryService.findTopBooksIds(1);

        ArgumentCaptor<Pageable> argumentCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(bookReviewRepository, times(1)).findTopNBooksIdsByAverageRate(argumentCaptor.capture());

        Pageable pageable = argumentCaptor.getValue();
        assertEquals(1, topBookIds.size());
        assertEquals(84, topBookIds.get(0));
        assertEquals(0, pageable.getPageNumber());
        assertEquals(1, pageable.getPageSize());
    }
}