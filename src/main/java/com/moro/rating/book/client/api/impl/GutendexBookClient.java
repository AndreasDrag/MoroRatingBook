package com.moro.rating.book.client.api.impl;

import com.moro.rating.book.client.api.BookClient;
import com.moro.rating.book.client.api.RestRequest;
import com.moro.rating.book.client.model.ClientBookDto;
import com.moro.rating.book.client.model.ClientSearchBooksResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GutendexBookClient implements BookClient {

    private static final String PAGE_QUERY_PARAM = "page";

    private static final String SEARCH_QUERY_PARAM = "search";

    private static final String BOOK_IDS_QUERY_PARAM = "ids";

    private static final String BOOK_IDS_DELIMITER = ",";

    private final String clientUrl;

    private final RestTemplate bookClientRestTemplate;

    public GutendexBookClient(@Value("${application.book-client.url}") String clientUrl, RestTemplate bookClientRestTemplate) {
        this.clientUrl = clientUrl;
        this.bookClientRestTemplate = bookClientRestTemplate;
    }

    @Override
    public ClientSearchBooksResponseDto searchBooks(String term, int page) {
        log.info("Calling client search books with term: {} and page: {}", term, page);
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add(PAGE_QUERY_PARAM, String.valueOf(page));
        queryParams.add(SEARCH_QUERY_PARAM, term);
        ResponseEntity<ClientSearchBooksResponseDto> response =
                new RestRequest<ClientSearchBooksResponseDto>(bookClientRestTemplate, clientUrl)
                        .queryParams(queryParams)
                        .responseType(ClientSearchBooksResponseDto.class)
                        .get();
        return response.getBody();
    }

    @Override
    public ClientBookDto getBook(Integer bookId) {
        log.info("Calling client get book with ID: {}", bookId);
        ResponseEntity<ClientBookDto> response =
                new RestRequest<ClientBookDto>(bookClientRestTemplate, clientUrl)
                        .pathVariable(String.valueOf(bookId))
                        .responseType(ClientBookDto.class)
                        .get();
        return response.getBody();
    }

    @Override
    public ClientSearchBooksResponseDto getBooksByIds(List<Integer> bookIds) {
        log.info("Calling client get books with IDS : {}", bookIds);
        String bookIdsParamValue = Arrays.stream(bookIds.toArray())
                .map(String::valueOf)
                .collect(Collectors.joining(BOOK_IDS_DELIMITER));

        ResponseEntity<ClientSearchBooksResponseDto> response =
                new RestRequest<ClientSearchBooksResponseDto>(bookClientRestTemplate, clientUrl)
                        .queryParam(BOOK_IDS_QUERY_PARAM, bookIdsParamValue)
                        .responseType(ClientSearchBooksResponseDto.class)
                        .get();
        return response.getBody();
    }
}
