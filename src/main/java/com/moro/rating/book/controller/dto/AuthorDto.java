package com.moro.rating.book.controller.dto;

public class AuthorDto {
    private final String name;
    private final String birthYear;
    private final String deathYear;

    public AuthorDto(Builder builder) {
        this.name = builder.name;
        this.birthYear = builder.birthYear;
        this.deathYear = builder.deathYear;
    }

    public String getName() {
        return name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getDeathYear() {
        return deathYear;
    }

    public static class Builder {
        private String name;
        private String birthYear;
        private String deathYear;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withBirthYear(String birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public Builder withDeathYear(String deathYear) {
            this.deathYear = deathYear;
            return this;
        }

        public AuthorDto build() {
            return new AuthorDto(this);
        }
    }
}
