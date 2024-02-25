package com.moro.rating.book.client.service;


import com.moro.rating.book.service.model.Book;

import java.util.List;

public interface BookClientService {
    List<Book> searchBooks(String description);

    Book getBook(Integer bookId);
}
