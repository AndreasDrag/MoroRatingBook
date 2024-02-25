package com.moro.rating.book.client.service.impl;

import com.moro.rating.book.client.api.BookClient;
import com.moro.rating.book.client.model.ClientSearchBooksResponseDto;
import com.moro.rating.book.client.service.BookClientService;
import com.moro.rating.book.client.transformer.BookDtoTransformer;
import com.moro.rating.book.service.model.Book;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class BookClientServiceImpl implements BookClientService {

    private final BookClient bookClient;

    public BookClientServiceImpl(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    public List<Book> searchBooks(String description) {
        return Optional.ofNullable(bookClient.searchBooks(description))
                .map(ClientSearchBooksResponseDto::getResults)
                .stream().flatMap(Collection::stream)
                .map(BookDtoTransformer::toModel)
                .toList();
    }

    @Override
    public Book getBook(Integer bookId) {
        return Optional.ofNullable(bookClient.getBook(bookId))
                .map(BookDtoTransformer::toModel)
                .orElse(null);
    }
}
