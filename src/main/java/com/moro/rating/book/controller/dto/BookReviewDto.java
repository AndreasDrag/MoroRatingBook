package com.moro.rating.book.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.moro.rating.book.controller.validator.annotation.IntegerId;
import com.moro.rating.book.controller.validator.annotation.Rate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonDeserialize(builder = BookReviewDto.Builder.class)
public class BookReviewDto {

    @Schema(description = "Book ID", example = "84")
    @NotNull(message = "Book ID is empty.")
    @IntegerId
    private final Integer bookId;

    @Schema(description = "Book Rate", example = "5")
    @NotNull(message = "Book rate is empty.")
    @Rate
    private final Integer rate;

    @Schema(description = "Book Review", example = "A book Review")
    @NotBlank(message = "Book review is empty.")
    private final String review;

    private BookReviewDto(Builder builder) {
        this.bookId = builder.bookId;
        this.rate = builder.rate;
        this.review = builder.review;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getRate() {
        return rate;
    }

    public String getReview() {
        return review;
    }

    public static class Builder {
        private Integer bookId;

        private Integer rate;

        private String review;

        public Builder withBookId(Integer bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder withRate(Integer rate) {
            this.rate = rate;
            return this;
        }

        public Builder withReview(String review) {
            this.review = review;
            return this;
        }

        public BookReviewDto build() {
            return new BookReviewDto(this);
        }
    }
}
