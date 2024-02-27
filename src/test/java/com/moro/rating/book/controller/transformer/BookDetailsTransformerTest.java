package com.moro.rating.book.controller.transformer;

import com.moro.rating.book.controller.dto.BookDetailsDto;
import com.moro.rating.book.service.model.Author;
import com.moro.rating.book.service.model.BookDetails;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookDetailsTransformerTest {

    @Test
    public void toDtoWhenModelIsNull() {
        assertNull(BookDetailsTransformer.toDto(null));
    }

    @Test
    public void toDtoWhenModelIsNotNullAndAuthorsAreNull() {
        BookDetails bookDetails = new BookDetails.Builder()
                .withId(84)
                .withTitle("Frankenstein")
                .withLanguages(List.of("en"))
                .withDownloadCount(100)
                .withAuthors(null)
                .withReviews(List.of("A Review"))
                .withRating(4.0)
                .build();

        BookDetailsDto dto = BookDetailsTransformer.toDto(bookDetails);

        assertEquals(84, dto.getId());
        assertEquals("Frankenstein", dto.getTitle());
        assertEquals(100, dto.getDownloadCount());
        assertNull(dto.getAuthors());
        assertEquals(1, dto.getLanguages().size());
        assertEquals("en", dto.getLanguages().get(0));
        assertEquals(1, dto.getReviews().size());
        assertEquals("A Review", dto.getReviews().get(0));
        assertEquals(4.0, dto.getRating());
    }

    @Test
    public void toDtoWhenModelIsNotNullAndLanguagesAreNull() {
        Author author = new Author.Builder()
                .withName("Shelley")
                .withBirthYear("1797")
                .withDeathYear("1897")
                .build();

        BookDetails bookDetails = new BookDetails.Builder()
                .withId(84)
                .withTitle("Frankenstein")
                .withLanguages(null)
                .withDownloadCount(100)
                .withAuthors(List.of(author))
                .withReviews(List.of("A Review"))
                .withRating(4.0)
                .build();

        BookDetailsDto bookDetailsDto = BookDetailsTransformer.toDto(bookDetails);

        assertEquals(84, bookDetailsDto.getId());
        assertEquals("Frankenstein", bookDetailsDto.getTitle());
        assertEquals(100, bookDetailsDto.getDownloadCount());
        assertEquals(1, bookDetailsDto.getAuthors().size());
        assertEquals("Shelley", bookDetailsDto.getAuthors().get(0).getName());
        assertEquals("1797", bookDetailsDto.getAuthors().get(0).getBirthYear());
        assertEquals("1897", bookDetailsDto.getAuthors().get(0).getDeathYear());
        assertNull(bookDetailsDto.getLanguages());
    }

    @Test
    public void toDtoWhenModelIsNotNull() {
        Author author = new Author.Builder()
                .withName("Shelley")
                .withBirthYear("1797")
                .withDeathYear("1897")
                .build();

        BookDetails bookDetails = new BookDetails.Builder()
                .withId(84)
                .withTitle("Frankenstein")
                .withLanguages(List.of("en"))
                .withDownloadCount(100)
                .withAuthors(List.of(author))
                .withReviews(List.of("A Review"))
                .withRating(4.0)
                .build();

        BookDetailsDto bookDetailsDto = BookDetailsTransformer.toDto(bookDetails);

        assertEquals(84, bookDetailsDto.getId());
        assertEquals("Frankenstein", bookDetailsDto.getTitle());
        assertEquals(100, bookDetailsDto.getDownloadCount());
        assertEquals(1, bookDetailsDto.getAuthors().size());
        assertEquals("Shelley", bookDetailsDto.getAuthors().get(0).getName());
        assertEquals("1797", bookDetailsDto.getAuthors().get(0).getBirthYear());
        assertEquals("1897", bookDetailsDto.getAuthors().get(0).getDeathYear());
        assertEquals(1, bookDetailsDto.getLanguages().size());
        assertEquals("en", bookDetailsDto.getLanguages().get(0));
    }
}