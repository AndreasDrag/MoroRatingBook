package com.moro.rating.book.controller;

import com.moro.rating.book.controller.dto.BookDetailsDto;
import com.moro.rating.book.controller.dto.BookDto;
import com.moro.rating.book.controller.dto.BookReviewDto;
import com.moro.rating.book.controller.service.MoroRatingBookApiService;
import com.moro.rating.book.controller.validator.annotation.IntegerId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class MoRatingBookController {

    private final MoroRatingBookApiService moroRatingBookApiService;

    public MoRatingBookController(MoroRatingBookApiService moroRatingBookApiService) {
        this.moroRatingBookApiService = moroRatingBookApiService;
    }

    @GetMapping("/search/{title}")
    @Operation(summary = "Search Book")
    public ResponseEntity<List<BookDto>> searchBook(
            @Parameter(description = "Book Title", required = true)
            @PathVariable("title")
            @NotNull(message = "Book title is empty.")
            String title) {
        return ResponseEntity.ok().body(moroRatingBookApiService.searchBook(title));
    }

    @PutMapping("/review")
    @Operation(summary = "Review Book")
    public ResponseEntity<HttpStatus> reviewBook(@RequestBody @Valid BookReviewDto bookReviewDto) {
        moroRatingBookApiService.reviewBook(bookReviewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/{bookId}")
    @Operation(summary = "Get Book Details")
    public ResponseEntity<BookDetailsDto> getBook(
            @Parameter(description = "Book Id", required = true)
            @PathVariable("bookId")
            @NotNull(message = "Book ID is empty.")
            @IntegerId
            Integer bookId) {
        return ResponseEntity.ok().body(moroRatingBookApiService.getBook(bookId));
    }

    @GetMapping("/get/top/{booksNumber}")
    @Operation(summary = "Get Book Details")
    public ResponseEntity<List<BookDto>> getTopBooks(
            @Parameter(description = "Top Books Number", required = true)
            @PathVariable("booksNumber")
            @NotNull(message = "Top Books NUmber is empty.")
            @IntegerId
            Integer booksNumber) {
        return ResponseEntity.ok().body(moroRatingBookApiService.getTopBooks(booksNumber));
    }

}
