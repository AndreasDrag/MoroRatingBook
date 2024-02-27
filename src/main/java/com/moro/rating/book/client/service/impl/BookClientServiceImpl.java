package com.moro.rating.book.client.service.impl;

import com.moro.rating.book.client.api.BookClient;
import com.moro.rating.book.client.exception.MoroRatingBookClientException;
import com.moro.rating.book.client.model.ClientSearchBooksResponseDto;
import com.moro.rating.book.client.service.BookClientService;
import com.moro.rating.book.client.transformer.BookDtoTransformer;
import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.PagedResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class BookClientServiceImpl implements BookClientService {

    private static final int BOOK_CLIENT_PAGE_SIZE = 32;

    private final BookClient bookClient;

    public BookClientServiceImpl(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    public PagedResult<List<Book>> searchBooks(String term, int page) {
        ClientSearchBooksResponseDto response = Optional.ofNullable(bookClient.searchBooks(term, page))
                .orElseThrow(() -> new MoroRatingBookClientException("Search Book Client Response is invalid."));

        if (CollectionUtils.isEmpty(response.getResults())) {
            log.error("Search Book Client Response is empty for term: {}", term);
            throw new MoroRatingBookClientException("Search Book Client Response is empty.");
        }

        return new PagedResult.Builder<List<Book>>()
                .withData(response.getResults()
                        .stream()
                        .map(BookDtoTransformer::toModel)
                        .toList())
                .withTotal(response.getCount())
                .withSize(BOOK_CLIENT_PAGE_SIZE)
                .withPage(page)
                .build();
    }

    @Override
    public Book getBook(Integer bookId) {
        return Optional.ofNullable(bookClient.getBook(bookId))
                .map(BookDtoTransformer::toModel)
                .orElseThrow(() -> new MoroRatingBookClientException("Get Book Client Response is invalid."));
    }
}
