package com.moro.rating.book.controller.transformer;

import com.moro.rating.book.controller.dto.BookReviewDto;
import com.moro.rating.book.service.model.BookReview;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookReviewDtoTransformerTest {

    @Test
    public void toModelWhenDtoIsNull() {
        assertNull(BookReviewDtoTransformer.toModel(null));
    }

    @Test
    public void toModelWhenDtoIsNotNull() {
        BookReviewDto bookReviewDto = new BookReviewDto.Builder()
                .withBookId(84)
                .withReview("A Review")
                .withRate(4)
                .build();

        BookReview bookReview = BookReviewDtoTransformer.toModel(bookReviewDto);

        assertEquals(84, bookReview.getBookId());
        assertEquals("A Review", bookReview.getReview());
        assertEquals(4, bookReview.getRate());
    }
}