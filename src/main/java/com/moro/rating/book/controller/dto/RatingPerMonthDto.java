package com.moro.rating.book.controller.dto;

/**
 * The type Book.
 */
public class RatingPerMonthDto {
    private final String createdDate;
    private final Double rating;

    private RatingPerMonthDto(Builder builder) {
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

        public RatingPerMonthDto build() {
            return new RatingPerMonthDto(this);
        }
    }
}
