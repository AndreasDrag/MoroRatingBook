package com.moro.rating.book.repository;

import com.moro.rating.book.repository.entity.BookReviewEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
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
        BookReviewEntity review1 = new BookReviewEntity();
        review1.setReview("Review1");
        review1.setBookId(84);
        review1.setRate(4);
        review1.setCreatedDate(LocalDate.now());

        BookReviewEntity review2 = new BookReviewEntity();
        review2.setReview("Review2");
        review2.setBookId(84);
        review2.setRate(3);
        review2.setCreatedDate(LocalDate.now());

        bookReviewRepository.save(review1);
        bookReviewRepository.save(review2);
    }

    @Test
    public void findAllByBookIdWhenBookReviewsExist() {
        Optional<List<BookReviewEntity>> bookReviews = bookReviewRepository.findAllByBookId(84);
        assertTrue(bookReviews.isPresent(), "Book reviews should exist.");
        assertEquals(84, bookReviews.get().get(0).getBookId());
        assertEquals(4, bookReviews.get().get(0).getRate());
        assertEquals("Review1", bookReviews.get().get(0).getReview());
    }

    @Test
    public void findTopNBooksIdsByAverageRateWhenPageSizeIsBiggerThanStoredElements() {
        Optional<List<Integer>> topBooks = bookReviewRepository.findTopNBooksIdsByAverageRate(PageRequest.of(0, 3));

        assertTrue(topBooks.isPresent(), "Book reviews should exist.");
        assertEquals(1, topBooks.get().size());
        assertEquals(84, topBooks.get().get(0));
    }
}