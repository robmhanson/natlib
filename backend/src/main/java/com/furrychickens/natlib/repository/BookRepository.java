package com.furrychickens.natlib.repository;

import com.furrychickens.natlib.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    Book findByISBN(String isbn);

    List<Book> findByTitle(String title);

}
