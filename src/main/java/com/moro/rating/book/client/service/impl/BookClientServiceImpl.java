package com.moro.rating.book.client.service.impl;

import com.moro.rating.book.client.api.BookClient;
import com.moro.rating.book.client.exception.MoroRatingBookClientException;
import com.moro.rating.book.client.model.ClientBookDto;
import com.moro.rating.book.client.model.ClientSearchBooksResponseDto;
import com.moro.rating.book.client.service.BookClientService;
import com.moro.rating.book.client.transformer.BookDtoTransformer;
import com.moro.rating.book.service.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class BookClientServiceImpl implements BookClientService {

    private final BookClient bookClient;

    public BookClientServiceImpl(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    public List<Book> searchBooks(String term) {
        List<ClientBookDto> clientBookDtos = Optional.ofNullable(bookClient.searchBooks(term))
                .map(ClientSearchBooksResponseDto::getResults)
                .orElseThrow(() -> new MoroRatingBookClientException("Search Book Client Response is invalid."));

        if (CollectionUtils.isEmpty(clientBookDtos)) {
            log.error("Search Book Client Response is empty for term: {}", term);
            throw new MoroRatingBookClientException("Search Book Client Response is empty.");
        }

        return clientBookDtos
                .stream()
                .map(BookDtoTransformer::toModel)
                .toList();
    }

    @Override
    public Book getBook(Integer bookId) {
        return Optional.ofNullable(bookClient.getBook(bookId))
                .map(BookDtoTransformer::toModel)
                .orElseThrow(() -> new MoroRatingBookClientException("Get Book Client Response is invalid."));
    }
}
