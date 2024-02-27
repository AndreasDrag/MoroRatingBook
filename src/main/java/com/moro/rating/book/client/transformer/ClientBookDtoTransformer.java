package com.moro.rating.book.client.transformer;


import com.moro.rating.book.client.model.ClientBookDto;
import com.moro.rating.book.service.model.Book;
import org.springframework.util.CollectionUtils;

public class ClientBookDtoTransformer {
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
                        CollectionUtils.isEmpty(clientBookDto.getAuthors())
                                ? null
                                : clientBookDto.getAuthors()
                                .stream()
                                .map(ClientAuthorDtoTransformer::toModel)
                                .toList()
                )
                .build();
    }
}
