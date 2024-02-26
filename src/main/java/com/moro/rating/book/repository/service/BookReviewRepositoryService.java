package com.moro.rating.book.repository.service;

import com.moro.rating.book.repository.entity.BookReviewEntity;
import com.moro.rating.book.service.model.BookReview;
import com.moro.rating.book.service.model.RatingPerMonth;

import java.util.List;

public interface BookReviewRepositoryService extends AbstractRepositoryService<BookReviewEntity, Long> {
    List<BookReview> findBookReviewsByBookId(Integer bookId);

    List<Integer> findTopBooksIds(Integer booksNumber);

    List<RatingPerMonth> findBookRatingPerMonth(Integer bookId);
}
