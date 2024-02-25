package com.moro.rating.book.service.api.impl;

import com.moro.rating.book.client.service.BookClientService;
import com.moro.rating.book.repository.service.BookReviewRepositoryService;
import com.moro.rating.book.repository.transformer.BookReviewEntityTransformer;
import com.moro.rating.book.service.api.MoroRatingBookService;
import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.BookDetails;
import com.moro.rating.book.service.model.BookReview;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Configuration
public class MoroRatingBookServiceImpl implements MoroRatingBookService {

    private final BookClientService bookClientService;

    private final BookReviewRepositoryService bookReviewRepositoryService;

    public MoroRatingBookServiceImpl(BookClientService bookClientService, BookReviewRepositoryService bookReviewRepositoryService) {
        this.bookClientService = bookClientService;
        this.bookReviewRepositoryService = bookReviewRepositoryService;
    }

    public List<Book> searchBook(String title) {
        return bookClientService.searchBooks(title);
    }

    @Override
    public void reviewBook(BookReview bookReview) {
        bookReviewRepositoryService.save(BookReviewEntityTransformer.toEntity(bookReview));
    }

    @Override
    public BookDetails getBook(Integer bookId) {
        Book book = bookClientService.getBook(bookId);
        List<BookReview> bookReviews = bookReviewRepositoryService.findBookReviewsByBookId(bookId);
        double averageRating = Optional.ofNullable(bookReviews).stream().flatMap(Collection::stream).map(BookReview::getRate)
                .mapToDouble(value -> value).average().orElse(0);
        return new BookDetails.Builder()
                .withId(book.getId())
                .withTitle(book.getTitle())
                .withRating(averageRating)
                .withReviews(
                        Optional.ofNullable(bookReviews)
                                .stream().flatMap(Collection::stream)
                                .map(BookReview::getReview)
                                .toList())
                .withLanguages(book.getLanguages())
                .withAuthors(book.getAuthors())
                .withDownloadCount(book.getDownloadCount())
                .build();
    }
}
