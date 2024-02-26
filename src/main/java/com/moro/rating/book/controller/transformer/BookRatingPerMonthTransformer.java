package com.moro.rating.book.controller.transformer;


import com.moro.rating.book.controller.dto.BookRatingPerMonthDto;
import com.moro.rating.book.service.model.BookRatingPerMonth;
import org.springframework.util.CollectionUtils;

public class BookRatingPerMonthTransformer {
    public static BookRatingPerMonthDto toDto(BookRatingPerMonth bookRatingPerMonth) {
        if (bookRatingPerMonth == null) {
            return null;
        }
        return new BookRatingPerMonthDto.Builder()
                .withId(bookRatingPerMonth.getId())
                .withRatingPerMonth(
                        CollectionUtils.isEmpty(bookRatingPerMonth.getRatingPerMonth())
                                ? null
                                : bookRatingPerMonth.getRatingPerMonth()
                                .stream().map(RatingPerMonthTransformer::toDto)
                                .toList())
                .build();
    }
}
