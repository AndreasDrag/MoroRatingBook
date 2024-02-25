package com.moro.rating.book.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ClientBookDto {

    private Integer id;

    private String title;

    private List<ClientAuthorDto> authors;

    private List<String> languages;

    @JsonProperty("download_count")
    private Integer downloadCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ClientAuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<ClientAuthorDto> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }
}
