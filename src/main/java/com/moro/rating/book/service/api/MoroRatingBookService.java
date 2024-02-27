package com.moro.rating.book.service.api;

import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.BookDetails;
import com.moro.rating.book.service.model.BookRatingPerMonth;
import com.moro.rating.book.service.model.BookReview;
import com.moro.rating.book.service.model.PagedResult;

import java.util.List;

public interface MoroRatingBookService {
    PagedResult<List<Book>> searchBooks(String term, int page);

    void reviewBook(BookReview bookReview);

    BookDetails getBook(Integer bookId);

    List<Book> getTopBooks(Integer booksNumber);

    BookRatingPerMonth getBookRatingPerMonth(Integer bookId);
}
