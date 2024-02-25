package com.moro.rating.book.controller.transformer;


import com.moro.rating.book.controller.dto.BookDetailsDto;
import com.moro.rating.book.service.model.BookDetails;
import org.springframework.util.CollectionUtils;

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
                        CollectionUtils.isEmpty(bookDetails.getAuthors())
                                ? null
                                : bookDetails.getAuthors()
                                .stream()
                                .map(AuthorTransformer::toDto).toList())
                .withLanguages(bookDetails.getLanguages())
                .withRating(bookDetails.getRating())
                .withReviews(bookDetails.getReviews())
                .build();
    }
}
