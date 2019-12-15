package com.furrychickens.natlib.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BorrowedBookDto {

    private Long id;
    private String title;
    private String author;
    private String isbn;

    public BorrowedBookDto(Long id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
}
