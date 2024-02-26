package com.moro.rating.book.repository.transformer;

import com.moro.rating.book.repository.entity.RatingPerMonthEntity;
import com.moro.rating.book.service.model.RatingPerMonth;

public class RatingPerMonthEntityTransformer {
    public static RatingPerMonth toModel(RatingPerMonthEntity ratingPerMonthEntity) {
        if (ratingPerMonthEntity == null) {
            return null;
        }
        return new RatingPerMonth.Builder()
                .withRating(ratingPerMonthEntity.getRating())
                .withCreatedDate(ratingPerMonthEntity.getCreatedDate())
                .build();
    }
}
