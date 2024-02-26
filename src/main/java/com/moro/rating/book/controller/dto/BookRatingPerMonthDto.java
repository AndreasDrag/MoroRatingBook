package com.moro.rating.book.controller.dto;

import java.util.List;

/**
 * The type Book.
 */
public class BookRatingPerMonthDto {
    private final Integer id;
    private final List<RatingPerMonthDto> ratingPerMonth;

    private BookRatingPerMonthDto(Builder builder) {
        this.id = builder.id;
        this.ratingPerMonth = builder.ratingPerMonth;
    }

    public Integer getId() {
        return id;
    }

    public List<RatingPerMonthDto> getRatingPerMonth() {
        return ratingPerMonth;
    }

    public static class Builder {
        private Integer id;
        private List<RatingPerMonthDto> ratingPerMonth;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withRatingPerMonth(List<RatingPerMonthDto> ratingPerMonth) {
            this.ratingPerMonth = ratingPerMonth;
            return this;
        }

        public BookRatingPerMonthDto build() {
            return new BookRatingPerMonthDto(this);
        }
    }
}
