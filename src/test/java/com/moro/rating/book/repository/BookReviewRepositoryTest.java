package com.moro.rating.book.repository;

import com.moro.rating.book.repository.entity.BookReviewEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookReviewRepositoryTest {

    @Autowired
    private BookReviewRepository bookReviewRepository;

    @BeforeAll
    public void saveAgreementContext() {
        BookReviewEntity review = new BookReviewEntity();
        review.setReview("Review1");
        review.setBookId(84);
        review.setRate(4);

        bookReviewRepository.save(review);
    }

    @Test
    public void findAllByBookIdWhenBookReviewsExist() {
        Optional<List<BookReviewEntity>> bookReviews = bookReviewRepository.findAllByBookId(84);
        assertTrue(bookReviews.isPresent(), "Book reviews should exist.");
        assertEquals(84, bookReviews.get().get(0).getBookId());
        assertEquals(4, bookReviews.get().get(0).getRate());
        assertEquals("Review1", bookReviews.get().get(0).getReview());
    }

}