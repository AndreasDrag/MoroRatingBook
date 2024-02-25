package com.moro.rating.book.repository.transformer;

import com.moro.rating.book.repository.entity.BookReviewEntity;
import com.moro.rating.book.service.model.BookReview;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class BookReviewEntityTransformerTest {

    @Test
    public void toModelWhenEntityIsNull() {
        assertNull(BookReviewEntityTransformer.toModel(null));
    }

    @Test
    public void toModelWhenEntityIsNotNull() {
        BookReviewEntity entity = new BookReviewEntity();
        entity.setReview("Review1");
        entity.setBookId(84);
        entity.setRate(4);
        BookReview model = BookReviewEntityTransformer.toModel(entity);

        assertEquals("Review1", model.getReview());
        assertEquals(4, model.getRate());
        assertEquals(84, model.getBookId());
    }

    @Test
    public void toEntityWhenModelIsNull() {
        assertNull(BookReviewEntityTransformer.toEntity(null));
    }

    @Test
    public void toEntityWhenModelIsNotNull() {
        BookReview bookReview = new BookReview.Builder()
                .withBookId(84)
                .withReview("Review1")
                .withRate(4)
                .build();
        BookReviewEntity entity = BookReviewEntityTransformer.toEntity(bookReview);

        assertEquals("Review1", entity.getReview());
        assertEquals(4, entity.getRate());
        assertEquals(84, entity.getBookId());
    }

}