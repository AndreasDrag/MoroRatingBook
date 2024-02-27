package com.moro.rating.book.client.service.impl;

import com.moro.rating.book.client.api.BookClient;
import com.moro.rating.book.client.exception.MoroRatingBookClientException;
import com.moro.rating.book.client.model.ClientSearchBooksResponseDto;
import com.moro.rating.book.client.service.BookClientService;
import com.moro.rating.book.client.transformer.ClientBookDtoTransformer;
import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.PagedResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

        return new PagedResult.Builder<List<Book>>()
                .withData(response.getResults()
                        .stream()
                        .map(ClientBookDtoTransformer::toModel)
                        .toList())
                .withTotal(response.getCount())
                .withSize(BOOK_CLIENT_PAGE_SIZE)
                .withPage(page)
                .build();
    }

    @Override
    public Book getBook(Integer bookId) {
        return Optional.ofNullable(bookClient.getBook(bookId))
                .map(ClientBookDtoTransformer::toModel)
                .orElseThrow(() -> new MoroRatingBookClientException("Book not found."));
    }

    @Override
    public List<Book> getBooksByIds(List<Integer> bookIds) {
        ClientSearchBooksResponseDto response = Optional.ofNullable(bookClient.getBooksByIds(bookIds))
                .orElseThrow(() -> new MoroRatingBookClientException("Get Books by ids Client Response is invalid."));

        return response.getResults()
                .stream()
                .map(ClientBookDtoTransformer::toModel)
                .toList();
    }
}
