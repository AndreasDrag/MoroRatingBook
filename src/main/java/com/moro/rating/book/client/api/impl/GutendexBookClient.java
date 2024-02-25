package com.moro.rating.book.client.api.impl;

import com.moro.rating.book.client.api.BookClient;
import com.moro.rating.book.client.api.RestRequest;
import com.moro.rating.book.client.model.ClientBookDto;
import com.moro.rating.book.client.model.ClientSearchBooksResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GutendexBookClient implements BookClient {

    private final String clientUrl;

    private final RestTemplate bookClientRestTemplate;

    public GutendexBookClient(@Value("${application.book-client.url}") String clientUrl, RestTemplate bookClientRestTemplate) {
        this.clientUrl = clientUrl;
        this.bookClientRestTemplate = bookClientRestTemplate;
    }

    public ClientSearchBooksResponseDto searchBooks(String description) {
        ResponseEntity<ClientSearchBooksResponseDto> search =
                new RestRequest<ClientSearchBooksResponseDto>(bookClientRestTemplate, clientUrl)
                        .queryParam("search", description)
                        .responseType(ClientSearchBooksResponseDto.class)
                        .get();
        return search.getBody();
    }

    @Override
    public ClientBookDto getBook(Integer bookId) {
        ResponseEntity<ClientBookDto> search =
                new RestRequest<ClientBookDto>(bookClientRestTemplate, clientUrl)
                        .pathVariable(String.valueOf(bookId))
                        .responseType(ClientBookDto.class)
                        .get();
        return search.getBody();
    }
}
