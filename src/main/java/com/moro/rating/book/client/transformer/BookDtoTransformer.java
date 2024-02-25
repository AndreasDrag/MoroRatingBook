package com.moro.rating.book.client.transformer;


import com.moro.rating.book.client.model.ClientBookDto;
import com.moro.rating.book.service.model.Book;

import java.util.Collection;
import java.util.Optional;

public class BookDtoTransformer {
    public static Book toModel(ClientBookDto clientBookDto) {
        if (clientBookDto == null) {
            return null;
        }
        return new Book.Builder()
                .withId(clientBookDto.getId())
                .withTitle(clientBookDto.getTitle())
                .withDownloadCount(clientBookDto.getDownloadCount())
                .withLanguages(clientBookDto.getLanguages())
                .withAuthors(
                        Optional.ofNullable(clientBookDto.getAuthors())
                                .stream().flatMap(Collection::stream)
                                .map(AuthorDtoTransformer::toModel)
                                .toList()
                )
                .build();
    }
}
