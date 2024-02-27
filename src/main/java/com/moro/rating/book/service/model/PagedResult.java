package com.moro.rating.book.service.model;

public class PagedResult<T> {
    private final int size;
    private final int page;
    private final long total;
    private final T data;

    public PagedResult(Builder<T> builder) {
        this.size = builder.size;
        this.page = builder.page;
        this.total = builder.total;
        this.data = builder.data;
    }

    public int getSize() {
        return size;
    }

    public int getPage() {
        return page;
    }

    public long getTotal() {
        return total;
    }

    public T getData() {
        return data;
    }

    public static class Builder<T> {
        private int size;
        private int page;
        private long total;
        private T data;

        public Builder<T> withSize(int size) {
            this.size = size;
            return this;
        }

        public Builder<T> withPage(int page) {
            this.page = page;
            return this;
        }

        public Builder<T> withTotal(long total) {
            this.total = total;
            return this;
        }

        public Builder<T> withData(T data) {
            this.data = data;
            return this;
        }

        public PagedResult<T> build() {
            return new PagedResult<>(this);
        }
    }
}