package com.moro.rating.book.client.service;


import com.moro.rating.book.service.model.Book;
import com.moro.rating.book.service.model.PagedResult;

import java.util.List;

public interface BookClientService {
    PagedResult<List<Book>> searchBooks(String term, int page);

    Book getBook(Integer bookId);
}
