package com.furrychickens.natlib.repository;

import com.furrychickens.natlib.model.Book;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveBook() {
        final Book book = new Book();
        book.setTitle("MyTitle");
        book.setAuthor("MyAuthor");
        book.setIsbn("MyIsbn");

        final Book updatedBook = bookRepository.save(book);

        assertThat(updatedBook, CoreMatchers.notNullValue());
        assertThat(updatedBook.getId(), CoreMatchers.notNullValue());
    }


    @Test
    void shouldFindBookByIsbn() {
        final Book book = new Book();
        book.setTitle("MyTitle");
        book.setAuthor("MyAuthor");
        book.setIsbn("MyIsbn");

        bookRepository.save(book);

        final Book foundBook = bookRepository.findByIsbn("MyIsbn");

        assertThat(foundBook, CoreMatchers.notNullValue());
    }

    @Test
    void shouldNotFindBookByInvalidIsbn() {
        final Book book = new Book();
        book.setTitle("MyTitle");
        book.setAuthor("MyAuthor");
        book.setIsbn("MyIsbn");

        bookRepository.save(book);

        final Book foundBook = bookRepository.findByIsbn("InvalidIsbn");

        assertThat(foundBook, CoreMatchers.nullValue());
    }
}
