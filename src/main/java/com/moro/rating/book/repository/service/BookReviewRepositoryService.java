package com.moro.rating.book.repository.service;

import com.moro.rating.book.repository.entity.BookReviewEntity;
import com.moro.rating.book.service.model.BookReview;

import java.util.List;

public interface BookReviewRepositoryService extends AbstractRepositoryService<BookReviewEntity, Long> {
    List<BookReview> findBookReviewsByBookId(Integer bookId);
}
