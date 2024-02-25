package com.moro.rating.book.controller.transformer;


import com.moro.rating.book.controller.dto.BookDto;
import com.moro.rating.book.service.model.Book;

import java.util.Collection;
import java.util.Optional;

public class BookTransformer {
    public static BookDto toDto(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDto.Builder()
                .withId(book.getId())
                .withTitle(book.getTitle())
                .withDownloadCount(book.getDownloadCount())
                .withAuthors(
                        Optional.ofNullable(book.getAuthors())
                                .stream().flatMap(Collection::stream)
                                .map(AuthorTransformer::toDto).toList())
                .withLanguages(book.getLanguages())
                .build();
    }
}
