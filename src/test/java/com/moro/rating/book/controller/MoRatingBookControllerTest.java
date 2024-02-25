package com.moro.rating.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moro.rating.book.controller.dto.BookDetailsDto;
import com.moro.rating.book.controller.dto.BookDto;
import com.moro.rating.book.controller.dto.BookReviewDto;
import com.moro.rating.book.controller.service.MoroRatingBookApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class MoRatingBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MoroRatingBookApiService moroRatingBookApiService;

    @Test
    public void searchBookThen200HttpStatusIsReceived() throws Exception {
        when(moroRatingBookApiService.searchBook(anyString())).thenReturn(List.of(new BookDto.Builder().build()));

        this.mockMvc.perform(get("/books/search/Frankenstein")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void reviewBookThen200HttpStatusIsReceived() throws Exception {
        BookReviewDto review = new BookReviewDto.Builder()
                .withBookId(84)
                .withRate(4)
                .withReview("A Review")
                .build();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        this.mockMvc.perform(put("/books/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(review)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void reviewBookWithEmptyReviewThen400HttpStatusIsReceived() throws Exception {
        BookReviewDto review = new BookReviewDto.Builder()
                .withBookId(84)
                .withRate(4)
                .build();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        this.mockMvc.perform(put("/books/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(review)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void reviewBookWithEmptyBookIdThen400HttpStatusIsReceived() throws Exception {
        BookReviewDto review = new BookReviewDto.Builder()
                .withRate(4)
                .withReview("A Review")
                .build();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        this.mockMvc.perform(put("/books/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(review)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void reviewBookWithEmptyRateThen400HttpStatusIsReceived() throws Exception {
        BookReviewDto review = new BookReviewDto.Builder()
                .withBookId(84)
                .withReview("A Review")
                .build();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        this.mockMvc.perform(put("/books/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(review)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getBookThen200HttpStatusIsReceived() throws Exception {
        when(moroRatingBookApiService.getBook(anyInt())).thenReturn(new BookDetailsDto.Builder().build());

        this.mockMvc.perform(get("/books/get/84")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}