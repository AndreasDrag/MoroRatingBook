package com.moro.rating.book.controller.transformer;

import com.moro.rating.book.controller.dto.BookReviewDto;
import com.moro.rating.book.service.model.BookReview;

public class BookReviewDtoTransformer {
    public static BookReview toModel(BookReviewDto bookReviewDto) {
        if (bookReviewDto == null) {
            return null;
        }
        return new BookReview.Builder()
                .withBookId(bookReviewDto.getBookId())
                .withRate(bookReviewDto.getRate())
                .withReview(bookReviewDto.getReview())
                .build();
    }
}
