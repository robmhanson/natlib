package com.furrychickens.natlib.service;

import com.furrychickens.natlib.exception.BookNotFoundException;
import com.furrychickens.natlib.exception.PersonNotFoundException;
import com.furrychickens.natlib.model.Book;
import com.furrychickens.natlib.model.Person;
import com.furrychickens.natlib.model.dto.BorrowBookDto;
import com.furrychickens.natlib.repository.BookRepository;
import com.furrychickens.natlib.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(final Long id) {
        return bookRepository.findById(id);
    }

    public Book save(final Book book) {
        return bookRepository.save(book);
    }

    public void returnBook(final Long id) {

        final Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setBorrowedBy(null);

            bookRepository.save(book);
        }
    }

    public List<Book> findByBorrowedById(final Long personId) {
        return bookRepository.findByBorrowedById(personId);
    }

    public void borrowBook(final BorrowBookDto borrowBookDto) {

        final Optional<Book> optionalBook = bookRepository.findById(borrowBookDto.getBookId());

        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException(borrowBookDto.getBookId());
        }

        final Optional<Person> optionalPerson = personRepository.findById(borrowBookDto.getPersonId());

        if (!optionalPerson.isPresent()) {
            throw new PersonNotFoundException(borrowBookDto.getPersonId());
        }

        final Book book = optionalBook.get();
        book.setBorrowedBy(optionalPerson.get());

        bookRepository.save(book);

    }
}
