package com.moro.rating.book.client.api;

import com.moro.rating.book.client.model.ClientBookDto;
import com.moro.rating.book.client.model.ClientSearchBooksResponseDto;

import java.util.List;

public interface BookClient {
    ClientSearchBooksResponseDto searchBooks(String term, int page);

    ClientBookDto getBook(Integer bookId);

    ClientSearchBooksResponseDto getBooksByIds(List<Integer> bookIds);
}
