package com.moro.rating.book.service.api.impl;

import com.moro.rating.book.client.service.BookClientService;
import com.moro.rating.book.repository.service.BookReviewRepositoryService;
import com.moro.rating.book.repository.transformer.BookReviewEntityTransformer;
import com.moro.rating.book.service.MoroRatingBookException;
import com.moro.rating.book.service.api.MoroRatingBookService;
import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.BookDetails;
import com.moro.rating.book.service.model.BookRatingPerMonth;
import com.moro.rating.book.service.model.BookReview;
import com.moro.rating.book.service.model.PagedResult;
import com.moro.rating.book.service.model.RatingPerMonth;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class MoroRatingBookServiceImpl implements MoroRatingBookService {

    private static final String SEARCH_BOOK_CACHE_NAME = "searchBook";

    private static final String GER_BOOK_CACHE_NAME = "getBook";

    private final BookClientService bookClientService;

    private final BookReviewRepositoryService bookReviewRepositoryService;

    public MoroRatingBookServiceImpl(BookClientService bookClientService, BookReviewRepositoryService bookReviewRepositoryService) {
        this.bookClientService = bookClientService;
        this.bookReviewRepositoryService = bookReviewRepositoryService;
    }

    @Cacheable(cacheNames = SEARCH_BOOK_CACHE_NAME, key = "#term + #page")
    @Override
    public PagedResult<List<Book>> searchBooks(String term, int page) {
        return bookClientService.searchBooks(term, page);
    }

    @CacheEvict(value = GER_BOOK_CACHE_NAME, key = "#bookReview.bookId")
    @Override
    public void reviewBook(BookReview bookReview) {
        bookReviewRepositoryService.save(BookReviewEntityTransformer.toEntity(bookReview));
    }

    @Cacheable(cacheNames = GER_BOOK_CACHE_NAME, key = "#bookId")
    @Override
    public BookDetails getBook(Integer bookId) {
        Book book = bookClientService.getBook(bookId);
        List<BookReview> bookReviews = bookReviewRepositoryService.findBookReviewsByBookId(bookId);
        double averageRating = Optional.ofNullable(bookReviews).stream()
                .flatMap(Collection::stream)
                .map(BookReview::getRate)
                .mapToDouble(value -> value)
                .average()
                .orElse(0);
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

    @Override
    public List<Book> getTopBooks(Integer booksNumber) {
        List<Integer> topBookIds = bookReviewRepositoryService.findTopBooksIds(booksNumber);
        if (CollectionUtils.isEmpty(topBookIds)) {
            throw new MoroRatingBookException("No ratings found.");
        }
        return bookClientService.getBooksByIds(topBookIds);
    }

    @Override
    public BookRatingPerMonth getBookRatingPerMonth(Integer bookId) {
        List<RatingPerMonth> bookRatingPerMonth = bookReviewRepositoryService.findBookRatingPerMonth(bookId);
        if (CollectionUtils.isEmpty(bookRatingPerMonth)) {
            throw new MoroRatingBookException("No ratings found.");
        }
        return new BookRatingPerMonth.Builder()
                .withId(bookId)
                .withRatingPerMonth(bookRatingPerMonth)
                .build();
    }
}
