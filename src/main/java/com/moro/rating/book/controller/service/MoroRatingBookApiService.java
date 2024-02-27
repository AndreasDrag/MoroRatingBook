package com.moro.rating.book.controller.service;

import com.moro.rating.book.controller.dto.BookDetailsDto;
import com.moro.rating.book.controller.dto.BookDto;
import com.moro.rating.book.controller.dto.BookRatingPerMonthDto;
import com.moro.rating.book.controller.dto.BookReviewDto;
import com.moro.rating.book.service.model.PagedResult;

import java.util.List;

public interface MoroRatingBookApiService {

    PagedResult<List<BookDto>> searchBooks(String term, int page);

    void reviewBook(BookReviewDto bookReviewDto);

    BookDetailsDto getBook(Integer bookId);

    List<BookDto> getTopBooks(Integer booksNumber);

    BookRatingPerMonthDto getBookRatingPerMonth(Integer bookId);
}
