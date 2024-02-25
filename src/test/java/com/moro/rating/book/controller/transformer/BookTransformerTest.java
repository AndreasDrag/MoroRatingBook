package com.moro.rating.book.controller.transformer;

import com.moro.rating.book.controller.dto.BookDto;
import com.moro.rating.book.service.model.Author;
import com.moro.rating.book.service.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class BookTransformerTest {

    @Test
    public void toDtoWhenModelIsNull() {
        assertNull(BookDetailsTransformer.toDto(null));
    }

    @Test
    public void toDtoWhenModelIsNotNullAndAuthorsAreNull() {
        Book book = new Book.Builder()
                .withId(84)
                .withTitle("Frankenstein")
                .withLanguages(List.of("en"))
                .withDownloadCount(100)
                .withAuthors(null)
                .build();

        BookDto dto = BookTransformer.toDto(book);

        assertEquals(84, dto.getId());
        assertEquals("Frankenstein", dto.getTitle());
        assertEquals(100, dto.getDownloadCount());
        assertNull(dto.getAuthors());
        assertEquals(1, dto.getLanguages().size());
        assertEquals("en", dto.getLanguages().get(0));
    }

    @Test
    public void toDtoWhenModelIsNotNullAndLanguagesAreNull() {
        Author author = new Author.Builder()
                .withName("Shelley")
                .withBirthYear("1797")
                .withDeathYear("1897")
                .build();

        Book book = new Book.Builder()
                .withId(84)
                .withTitle("Frankenstein")
                .withLanguages(null)
                .withDownloadCount(100)
                .withAuthors(List.of(author))
                .build();

        BookDto dto = BookTransformer.toDto(book);

        assertEquals(84, dto.getId());
        assertEquals("Frankenstein", dto.getTitle());
        assertEquals(100, dto.getDownloadCount());
        assertEquals(1, dto.getAuthors().size());
        assertEquals("Shelley", dto.getAuthors().get(0).getName());
        assertEquals("1797", dto.getAuthors().get(0).getBirthYear());
        assertEquals("1897", dto.getAuthors().get(0).getDeathYear());
        assertNull(dto.getLanguages());
    }

    @Test
    public void toDtoWhenModelIsNotNull() {
        Author author = new Author.Builder()
                .withName("Shelley")
                .withBirthYear("1797")
                .withDeathYear("1897")
                .build();

        Book book = new Book.Builder()
                .withId(84)
                .withTitle("Frankenstein")
                .withLanguages(List.of("en"))
                .withDownloadCount(100)
                .withAuthors(List.of(author))
                .build();

        BookDto dto = BookTransformer.toDto(book);

        assertEquals(84, dto.getId());
        assertEquals("Frankenstein", dto.getTitle());
        assertEquals(100, dto.getDownloadCount());
        assertEquals(1, dto.getAuthors().size());
        assertEquals("Shelley", dto.getAuthors().get(0).getName());
        assertEquals("1797", dto.getAuthors().get(0).getBirthYear());
        assertEquals("1897", dto.getAuthors().get(0).getDeathYear());
        assertEquals(1, dto.getLanguages().size());
        assertEquals("en", dto.getLanguages().get(0));
    }
}