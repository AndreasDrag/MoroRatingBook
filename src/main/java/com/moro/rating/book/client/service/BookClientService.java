package com.moro.rating.book.client.service;


import com.moro.rating.book.service.model.Book;

import java.util.List;

public interface BookClientService {
    List<Book> searchBooks(String term);

    Book getBook(Integer bookId);
}
