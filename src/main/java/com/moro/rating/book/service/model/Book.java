package com.moro.rating.book.service.model;

import java.util.List;

/**
 * The type Book.
 */
public class Book {
    private final Integer id;
    private final String title;
    private final List<Author> authors;
    private final List<String> languages;
    private final Integer downloadCount;

    private Book(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.authors = builder.authors;
        this.languages = builder.languages;
        this.downloadCount = builder.downloadCount;
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

    public static class Builder {
        private Integer id;
        private String title;
        private List<Author> authors;
        private List<String> languages;
        private Integer downloadCount;

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

        public Book build() {
            return new Book(this);
        }
    }
}
