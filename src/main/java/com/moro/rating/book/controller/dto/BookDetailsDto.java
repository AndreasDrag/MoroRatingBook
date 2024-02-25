package com.moro.rating.book.controller.dto;

import java.util.List;

/**
 * The type Book.
 */
public class BookDetailsDto {
    private final Integer id;
    private final String title;
    private final List<AuthorDto> authorDtos;
    private final List<String> languages;
    private final Integer downloadCount;
    private final Double rating;
    private final List<String> reviews;

    private BookDetailsDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.authorDtos = builder.authorDtos;
        this.languages = builder.languages;
        this.downloadCount = builder.downloadCount;
        this.rating = builder.rating;
        this.reviews = builder.reviews;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<AuthorDto> getAuthors() {
        return authorDtos;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public Double getRating() {
        return rating;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public static class Builder {
        private Integer id;
        private String title;
        private List<AuthorDto> authorDtos;
        private List<String> languages;
        private Integer downloadCount;
        private Double rating;
        private List<String> reviews;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAuthors(List<AuthorDto> authorDtos) {
            this.authorDtos = authorDtos;
            return this;
        }

        public Builder withLanguages(List<String> languages) {
            this.languages = languages;
            return this;
        }

        public Builder withDownloadCount(Integer downloadCount) {
            this.downloadCount = downloadCount;
            return this;
        }

        public Builder withRating(Double rate) {
            this.rating = rate;
            return this;
        }

        public Builder withReviews(List<String> reviews) {
            this.reviews = reviews;
            return this;
        }

        public BookDetailsDto build() {
            return new BookDetailsDto(this);
        }
    }
}
