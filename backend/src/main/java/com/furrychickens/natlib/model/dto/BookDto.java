package com.furrychickens.natlib.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Valid
public class BookDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private String isbn;

    @NotNull
    private Long borrowedBy;

    public BookDto(Long id, String title, String author, String isbn, Long borrowedBy) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.borrowedBy = borrowedBy;
    }
}
