package com.moro.rating.book.repository.transformer;

import com.moro.rating.book.repository.entity.BookReviewEntity;
import com.moro.rating.book.service.model.BookReview;

public class BookReviewEntityTransformer {
    public static BookReview toModel(BookReviewEntity bookReviewEntity) {
        if (bookReviewEntity == null) {
            return null;
        }
        return new BookReview.Builder()
                .withBookId(bookReviewEntity.getBookId())
                .withRate(bookReviewEntity.getRate())
                .withReview(bookReviewEntity.getReview())
                .build();
    }

    public static BookReviewEntity toEntity(BookReview bookReview) {
        if (bookReview == null) {
            return null;
        }
        BookReviewEntity bookReviewEntity = new BookReviewEntity();
        bookReviewEntity.setBookId(bookReview.getBookId());
        bookReviewEntity.setRate(bookReview.getRate());
        bookReviewEntity.setReview(bookReview.getReview());
        return bookReviewEntity;
    }
}
