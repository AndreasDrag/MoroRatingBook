package com.moro.rating.book.controller.transformer;


import com.moro.rating.book.controller.dto.BookDetailsDto;
import com.moro.rating.book.service.model.BookDetails;

import java.util.Collection;
import java.util.Optional;

public class BookDetailsTransformer {
    public static BookDetailsDto toDto(BookDetails bookDetails) {
        if (bookDetails == null) {
            return null;
        }
        return new BookDetailsDto.Builder()
                .withId(bookDetails.getId())
                .withTitle(bookDetails.getTitle())
                .withDownloadCount(bookDetails.getDownloadCount())
                .withAuthors(
                        Optional.ofNullable(bookDetails.getAuthors())
                                .stream().flatMap(Collection::stream)
                                .map(AuthorTransformer::toDto).toList())
                .withLanguages(bookDetails.getLanguages())
                .withRating(bookDetails.getRating())
                .withReviews(bookDetails.getReviews())
                .build();
    }
}
