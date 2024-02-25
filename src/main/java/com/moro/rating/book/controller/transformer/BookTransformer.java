package com.moro.rating.book.controller.transformer;


import com.moro.rating.book.controller.dto.BookDto;
import com.moro.rating.book.service.model.Book;
import org.springframework.util.CollectionUtils;

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
                        CollectionUtils.isEmpty(book.getAuthors())
                                ? null
                                : book.getAuthors()
                                .stream()
                                .map(AuthorTransformer::toDto).toList())
                .withLanguages(book.getLanguages())
                .build();
    }
}
