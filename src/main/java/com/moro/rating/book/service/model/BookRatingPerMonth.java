package com.moro.rating.book.service.model;

import java.util.List;

public class BookRatingPerMonth {
    private final Integer id;
    private final List<RatingPerMonth> ratingPerMonth;

    private BookRatingPerMonth(Builder builder) {
        this.id = builder.id;
        this.ratingPerMonth = builder.ratingPerMonth;
    }

    public Integer getId() {
        return id;
    }

    public List<RatingPerMonth> getRatingPerMonth() {
        return ratingPerMonth;
    }

    public static class Builder {
        private Integer id;
        private List<RatingPerMonth> ratingPerMonth;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withRatingPerMonth(List<RatingPerMonth> ratingPerMonth) {
            this.ratingPerMonth = ratingPerMonth;
            return this;
        }

        public BookRatingPerMonth build() {
            return new BookRatingPerMonth(this);
        }
    }
}
