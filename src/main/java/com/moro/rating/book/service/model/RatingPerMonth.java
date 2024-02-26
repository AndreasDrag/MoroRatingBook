package com.moro.rating.book.service.model;

/**
 * The type Book.
 */
public class RatingPerMonth {
    private final String createdDate;
    private final Double rating;

    private RatingPerMonth(Builder builder) {
        this.createdDate = builder.createdDate;
        this.rating = builder.rating;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Double getRating() {
        return rating;
    }

    public static class Builder {
        private String createdDate;
        private Double rating;

        public Builder withCreatedDate(String createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder withRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public RatingPerMonth build() {
            return new RatingPerMonth(this);
        }
    }
}
