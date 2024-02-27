package com.moro.rating.book.client.transformer;

import com.moro.rating.book.client.model.ClientAuthorDto;
import com.moro.rating.book.service.model.Author;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientAuthorDtoTransformerTest {

    @Test
    public void toModelWhenDtoIsNull() {
        assertNull(ClientAuthorDtoTransformer.toModel(null));
    }

    @Test
    public void toModelWhenDtoIsNotNull() {
        ClientAuthorDto clientAuthorDto = new ClientAuthorDto();
        clientAuthorDto.setName("Shelley");
        clientAuthorDto.setBirthYear("1797");
        clientAuthorDto.setDeathYear("1897");

        Author author = ClientAuthorDtoTransformer.toModel(clientAuthorDto);

        assertEquals("Shelley", author.getName());
        assertEquals("1797", author.getBirthYear());
        assertEquals("1897", author.getDeathYear());
    }

}