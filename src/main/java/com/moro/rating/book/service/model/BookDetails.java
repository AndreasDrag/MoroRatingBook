package com.moro.rating.book.service.model;

import java.util.List;

/**
 * The type Book.
 */
public class BookDetails {
    private final Integer id;
    private final String title;
    private final List<Author> authors;
    private final List<String> languages;
    private final Integer downloadCount;
    private final Double rating;
    private final List<String> reviews;

    private BookDetails(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.authors = builder.authors;
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

    public List<Author> getAuthors() {
        return authors;
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
        private List<Author> authors;
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

        public Builder withAuthors(List<Author> authors) {
            this.authors = authors;
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

        public BookDetails build() {
            return new BookDetails(this);
        }
    }
}
