package com.moro.rating.book.controller.service.impl;

import com.moro.rating.book.controller.dto.BookDetailsDto;
import com.moro.rating.book.controller.dto.BookDto;
import com.moro.rating.book.controller.dto.BookRatingPerMonthDto;
import com.moro.rating.book.controller.dto.BookReviewDto;
import com.moro.rating.book.controller.service.MoroRatingBookApiService;
import com.moro.rating.book.controller.transformer.BookDetailsTransformer;
import com.moro.rating.book.controller.transformer.BookRatingPerMonthTransformer;
import com.moro.rating.book.controller.transformer.BookReviewDtoTransformer;
import com.moro.rating.book.controller.transformer.BookTransformer;
import com.moro.rating.book.service.api.MoroRatingBookService;
import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.PagedResult;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class MoroRatingBookApiServiceImpl implements MoroRatingBookApiService {

    private final MoroRatingBookService moroRatingBookService;

    public MoroRatingBookApiServiceImpl(MoroRatingBookService moroRatingBookService) {
        this.moroRatingBookService = moroRatingBookService;
    }

    @Override
    public PagedResult<List<BookDto>> searchBooks(String term, int page) {
        return Optional.ofNullable(moroRatingBookService.searchBooks(term, page))
                .map(result -> new PagedResult.Builder<List<BookDto>>()
                        .withData(toBookDtoList(result.getData()))
                        .withTotal(result.getTotal())
                        .withSize(result.getSize())
                        .withPage(result.getPage())
                        .build())
                .orElse(null);
    }

    private static List<BookDto> toBookDtoList(List<Book> result) {
        return Optional.ofNullable(result)
                .stream().flatMap(Collection::stream)
                .map(BookTransformer::toDto)
                .toList();
    }

    @Override
    public void reviewBook(BookReviewDto bookReviewDto) {
        moroRatingBookService.reviewBook(Optional.ofNullable(bookReviewDto)
                .map(BookReviewDtoTransformer::toModel)
                .orElse(null));
    }

    @Override
    public BookDetailsDto getBook(Integer bookId) {
        return Optional.ofNullable(moroRatingBookService.getBook(bookId))
                .map(BookDetailsTransformer::toDto)
                .orElse(null);
    }

    @Override
    public List<BookDto> getTopBooks(Integer booksNumber) {
        return Optional.ofNullable(moroRatingBookService.getTopBooks(booksNumber))
                .stream().flatMap(Collection::stream)
                .map(BookTransformer::toDto)
                .toList();
    }

    @Override
    public BookRatingPerMonthDto getBookRatingPerMonth(Integer bookId) {
        return Optional.ofNullable(moroRatingBookService.getBookRatingPerMonth(bookId))
                .map(BookRatingPerMonthTransformer::toDto)
                .orElse(null);
    }
}
