package com.furrychickens.natlib.model;

import com.furrychickens.natlib.model.dto.BookDto;
import com.furrychickens.natlib.model.dto.BorrowedBookDto;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String author;

    @Column(unique = true)
    private String isbn;

    @ManyToOne(optional = true)
    private Person borrowedBy;

    public Book() {
    }

    public Book(final String title, final String author, final String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public BorrowedBookDto toBorrowedBook() {
        return new BorrowedBookDto(id, title, author, isbn);
    }

    public BookDto toBookDto() {
        if (borrowedBy == null) {
            return new BookDto(id, title, author, isbn, null) ;
        } else {
            return new BookDto(id, title, author, isbn, borrowedBy.getId()) ;
        }
    }
}

