package com.moro.rating.book.controller.transformer;

import com.moro.rating.book.controller.dto.RatingPerMonthDto;
import com.moro.rating.book.service.model.RatingPerMonth;

public class RatingPerMonthTransformer {
    public static RatingPerMonthDto toDto(RatingPerMonth ratingPerMonth) {
        if (ratingPerMonth == null) {
            return null;
        }
        return new RatingPerMonthDto.Builder()
                .withRating(ratingPerMonth.getRating())
                .withCreatedDate(ratingPerMonth.getCreatedDate())
                .build();
    }
}
