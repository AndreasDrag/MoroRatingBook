package com.moro.rating.book.repository.entity;

public class RatingPerMonthEntity {
    private Object createdDate;

    private Double rating;

    public RatingPerMonthEntity() {
    }

    public RatingPerMonthEntity(String createdDate, Double rating) {
        this.createdDate = createdDate;
        this.rating = rating;
    }

    public String getCreatedDate() {
        return createdDate.toString();
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
