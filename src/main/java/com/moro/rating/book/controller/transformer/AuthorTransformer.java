package com.moro.rating.book.controller.transformer;


import com.moro.rating.book.controller.dto.AuthorDto;
import com.moro.rating.book.service.model.Author;

public class AuthorTransformer {
    public static AuthorDto toDto(Author author) {
        if (author == null) {
            return null;
        }
        return new AuthorDto.Builder()
                .withName(author.getName())
                .withBirthYear(author.getBirthYear())
                .withDeathYear(author.getBirthYear())
                .build();
    }
}
