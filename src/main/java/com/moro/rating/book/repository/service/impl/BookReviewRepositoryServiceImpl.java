package com.moro.rating.book.repository.service.impl;

import com.moro.rating.book.repository.BookReviewRepository;
import com.moro.rating.book.repository.entity.BookReviewEntity;
import com.moro.rating.book.repository.service.BookReviewRepositoryService;
import com.moro.rating.book.repository.transformer.BookReviewEntityTransformer;
import com.moro.rating.book.service.model.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookReviewRepositoryServiceImpl extends AbstractRepositoryServiceImpl<BookReviewEntity, Long>
        implements BookReviewRepositoryService {

    private final BookReviewRepository bookReviewRepository;

    public BookReviewRepositoryServiceImpl(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    @Override
    public List<BookReview> findBookReviewsByBookId(Integer bookId) {
        return bookReviewRepository.findAllByBookId(bookId)
                .stream().flatMap(Collection::stream)
                .map(BookReviewEntityTransformer::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public JpaRepository<BookReviewEntity, Long> getRepository() {
        return bookReviewRepository;
    }
}
