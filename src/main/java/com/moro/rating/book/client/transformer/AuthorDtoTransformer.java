package com.moro.rating.book.client.transformer;


import com.moro.rating.book.client.model.ClientAuthorDto;
import com.moro.rating.book.service.model.Author;

public class AuthorDtoTransformer {
    public static Author toModel(ClientAuthorDto clientAuthorDto) {
        if (clientAuthorDto == null) {
            return null;
        }
        return new Author.Builder()
                .withName(clientAuthorDto.getName())
                .withBirthYear(clientAuthorDto.getBirthYear())
                .withDeathYear(clientAuthorDto.getDeathYear())
                .build();
    }
}
