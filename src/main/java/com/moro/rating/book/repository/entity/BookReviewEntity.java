package com.moro.rating.book.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOK_REVIEW")
public final class BookReviewEntity {

    @Id
    @SequenceGenerator(name = "book_review_seq", sequenceName = "book_review_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_review_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "BOOK_ID", nullable = false)
    private Integer bookId;

    @Column(name = "RATE", nullable = false)
    private Integer rate;

    @Column(name = "REVIEW", nullable = false)
    private String review;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
