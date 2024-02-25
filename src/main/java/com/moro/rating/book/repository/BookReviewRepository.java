package com.moro.rating.book.repository;

import com.moro.rating.book.repository.entity.BookReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReviewEntity, Long> {

    Optional<List<BookReviewEntity>> findAllByBookId(Integer bookId);
}
