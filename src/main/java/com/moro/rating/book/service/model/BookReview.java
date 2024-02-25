package com.moro.rating.book.service.model;

public class BookReview {

    private final Integer bookId;

    private final Integer rate;

    private final String review;

    private BookReview(Builder builder) {
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

        public BookReview build() {
            return new BookReview(this);
        }
    }
}
