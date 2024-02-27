package com.moro.rating.book.service;

public class MoroRatingBookException extends RuntimeException {

    public MoroRatingBookException(String message) {
        super(message);
    }

    public MoroRatingBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
