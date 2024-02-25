package com.moro.rating.book.controller.service;

import com.moro.rating.book.controller.dto.BookDetailsDto;
import com.moro.rating.book.controller.dto.BookDto;
import com.moro.rating.book.controller.dto.BookReviewDto;

import java.util.List;

public interface MoroRatingBookApiService {

    List<BookDto> searchBook(String title);

    void reviewBook(BookReviewDto bookReviewDto);

    BookDetailsDto getBook(Integer bookId);
}
