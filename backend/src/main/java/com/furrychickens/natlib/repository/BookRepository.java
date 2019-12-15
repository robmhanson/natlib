package com.furrychickens.natlib.repository;

import com.furrychickens.natlib.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBorrowedById(Long personId);

    Book findByIsbn(String isbn);
}
