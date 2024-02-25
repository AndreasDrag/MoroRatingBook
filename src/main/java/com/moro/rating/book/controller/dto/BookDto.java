package com.moro.rating.book.controller.dto;

import java.util.List;

/**
 * The type Book.
 */
public class BookDto {
    private final Integer id;
    private final String title;
    private final List<AuthorDto> authorDtos;
    private final List<String> languages;
    private final Integer downloadCount;

    private BookDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.authorDtos = builder.authorDtos;
        this.languages = builder.languages;
        this.downloadCount = builder.downloadCount;
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

    public static class Builder {
        private Integer id;
        private String title;
        private List<AuthorDto> authorDtos;
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

        public BookDto build() {
            return new BookDto(this);
        }
    }
}
