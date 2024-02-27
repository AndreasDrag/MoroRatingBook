package com.moro.rating.book.controller.transformer;

import com.moro.rating.book.controller.dto.AuthorDto;
import com.moro.rating.book.service.model.Author;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuthorTransformerTest {

    @Test
    public void toDtoWhenModelIsNull() {
        assertNull(AuthorTransformer.toDto(null));
    }

    @Test
    public void toDtoWhenModelIsNotNull() {
        Author author = new Author.Builder()
                .withName("Shelley")
                .withBirthYear("1797")
                .withDeathYear("1897")
                .build();

        AuthorDto authorDto = AuthorTransformer.toDto(author);

        assertEquals("Shelley", authorDto.getName());
        assertEquals("1797", authorDto.getBirthYear());
        assertEquals("1897", authorDto.getDeathYear());
    }

}