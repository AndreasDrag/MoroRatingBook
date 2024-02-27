package com.moro.rating.book.client.transformer;

import com.moro.rating.book.client.model.ClientAuthorDto;
import com.moro.rating.book.client.model.ClientBookDto;
import com.moro.rating.book.service.model.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientBookDtoTransformerTest {

    @Test
    public void toModelWhenDtoIsNull() {
        assertNull(ClientBookDtoTransformer.toModel(null));
    }

    @Test
    public void toModelWhenDtoIsNotNullAndAuthorsAreNull() {
        ClientBookDto clientBookDto = new ClientBookDto();
        clientBookDto.setId(84);
        clientBookDto.setTitle("Frankenstein");
        clientBookDto.setLanguages(List.of("en"));
        clientBookDto.setDownloadCount(100);
        clientBookDto.setAuthors(null);

        Book book = ClientBookDtoTransformer.toModel(clientBookDto);

        assertEquals(84, book.getId());
        assertEquals("Frankenstein", book.getTitle());
        assertEquals(100, book.getDownloadCount());
        assertNull(book.getAuthors());
        assertEquals(1, book.getLanguages().size());
        assertEquals("en", book.getLanguages().get(0));
    }

    @Test
    public void toModelWhenDtoIsNotNullAndLanguagesAreNull() {
        ClientAuthorDto clientAuthorDto = new ClientAuthorDto();
        clientAuthorDto.setName("Shelley");
        clientAuthorDto.setBirthYear("1797");
        clientAuthorDto.setDeathYear("1897");

        ClientBookDto clientBookDto = new ClientBookDto();
        clientBookDto.setId(84);
        clientBookDto.setTitle("Frankenstein");
        clientBookDto.setLanguages(null);
        clientBookDto.setDownloadCount(100);
        clientBookDto.setAuthors(List.of(clientAuthorDto));

        Book book = ClientBookDtoTransformer.toModel(clientBookDto);

        assertEquals(84, book.getId());
        assertEquals("Frankenstein", book.getTitle());
        assertEquals(100, book.getDownloadCount());
        assertEquals(1, book.getAuthors().size());
        assertEquals("Shelley", book.getAuthors().get(0).getName());
        assertEquals("1797", book.getAuthors().get(0).getBirthYear());
        assertEquals("1897", book.getAuthors().get(0).getDeathYear());
        assertNull(book.getLanguages());
    }

    @Test
    public void toModelWhenDtoIsNotNull() {
        ClientAuthorDto clientAuthorDto = new ClientAuthorDto();
        clientAuthorDto.setName("Shelley");
        clientAuthorDto.setBirthYear("1797");
        clientAuthorDto.setDeathYear("1897");

        ClientBookDto clientBookDto = new ClientBookDto();
        clientBookDto.setId(84);
        clientBookDto.setTitle("Frankenstein");
        clientBookDto.setLanguages(List.of("en"));
        clientBookDto.setDownloadCount(100);
        clientBookDto.setAuthors(List.of(clientAuthorDto));

        Book book = ClientBookDtoTransformer.toModel(clientBookDto);

        assertEquals(84, book.getId());
        assertEquals("Frankenstein", book.getTitle());
        assertEquals(100, book.getDownloadCount());
        assertEquals(1, book.getAuthors().size());
        assertEquals("Shelley", book.getAuthors().get(0).getName());
        assertEquals("1797", book.getAuthors().get(0).getBirthYear());
        assertEquals("1897", book.getAuthors().get(0).getDeathYear());
        assertEquals(1, book.getLanguages().size());
        assertEquals("en", book.getLanguages().get(0));
    }

}