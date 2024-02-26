package com.moro.rating.book.controller.service.impl;

import com.moro.rating.book.controller.dto.BookDetailsDto;
import com.moro.rating.book.controller.dto.BookDto;
import com.moro.rating.book.controller.dto.BookReviewDto;
import com.moro.rating.book.controller.service.MoroRatingBookApiService;
import com.moro.rating.book.controller.transformer.BookDetailsTransformer;
import com.moro.rating.book.controller.transformer.BookReviewDtoTransformer;
import com.moro.rating.book.controller.transformer.BookTransformer;
import com.moro.rating.book.service.api.MoroRatingBookService;
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
    public List<BookDto> searchBook(String title) {
        return Optional.ofNullable(moroRatingBookService.searchBook(title))
                .stream().flatMap(Collection::stream)
                .map(BookTransformer::toDto)
                .toList();
    }

    @Override
    public void reviewBook(BookReviewDto bookReviewDto) {
        moroRatingBookService.reviewBook(Optional.ofNullable(bookReviewDto)
                .map(BookReviewDtoTransformer::toModel).orElse(null));
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
}
