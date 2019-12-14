package com.furrychickens.natlib.repository;

import com.furrychickens.natlib.model.Book;
import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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
        book.setISBN("MyISBN");

        final Book updatedBook = bookRepository.save(book);

        assertThat(updatedBook, CoreMatchers.notNullValue());
        assertThat(updatedBook.getId(), CoreMatchers.notNullValue());
    }

    @Test
    void shouldFindBookByTitle() {
        final Book book = new Book();
        book.setTitle("MyTitle");
        book.setAuthor("MyAuthor");
        book.setISBN("MyISBN");

        bookRepository.save(book);

        final List<Book> foundBooks = bookRepository.findByTitle("MyTitle");

        assertThat(foundBooks, IsCollectionWithSize.hasSize(1));
        assertThat(foundBooks.get(0).getTitle(), CoreMatchers.is("MyTitle"));
    }

    @Test
    void shouldNotFindBookByInvalidTitle() {
        final Book book = new Book();
        book.setTitle("MyTitle");
        book.setAuthor("MyAuthor");
        book.setISBN("MyISBN");

        bookRepository.save(book);

        final List<Book> foundBooks = bookRepository.findByTitle("InvalidTitle");

        assertThat(foundBooks, IsCollectionWithSize.hasSize(0));
    }

    @Test
    void shouldFindBookByISBN() {
        final Book book = new Book();
        book.setTitle("MyTitle");
        book.setAuthor("MyAuthor");
        book.setISBN("MyISBN");

        bookRepository.save(book);

        final Book foundBook = bookRepository.findByISBN("MyISBN");

        assertThat(foundBook, CoreMatchers.notNullValue());
    }

    @Test
    void shouldNotFindBookByInvalidISBN() {
        final Book book = new Book();
        book.setTitle("MyTitle");
        book.setAuthor("MyAuthor");
        book.setISBN("MyISBN");

        bookRepository.save(book);

        final Book foundBook = bookRepository.findByISBN("InvalidISBN");

        assertThat(foundBook, CoreMatchers.nullValue());
    }
}
