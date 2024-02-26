package com.moro.rating.book.service.api;

import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.BookDetails;
import com.moro.rating.book.service.model.BookRatingPerMonth;
import com.moro.rating.book.service.model.BookReview;

import java.util.List;

public interface MoroRatingBookService {
    List<Book> searchBook(String title);

    void reviewBook(BookReview bookReview);

    BookDetails getBook(Integer bookId);

    List<Book> getTopBooks(Integer booksNumber);

    BookRatingPerMonth getBookRatingPerMonth(Integer bookId);
}
