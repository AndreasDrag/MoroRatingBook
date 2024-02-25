package com.moro.rating.book.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientAuthorDto {
    private String name;

    @JsonProperty("birth_year")
    private String birthYear;

    @JsonProperty("death_year")
    private String deathYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(String deathYear) {
        this.deathYear = deathYear;
    }
}
