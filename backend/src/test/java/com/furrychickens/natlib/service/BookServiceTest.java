package com.furrychickens.natlib.service;

import com.furrychickens.natlib.exception.BookNotFoundException;
import com.furrychickens.natlib.exception.PersonNotFoundException;
import com.furrychickens.natlib.model.Book;
import com.furrychickens.natlib.model.Person;
import com.furrychickens.natlib.model.dto.BorrowBookDto;
import com.furrychickens.natlib.repository.BookRepository;
import com.furrychickens.natlib.repository.PersonRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {

    private static final Long VALID_BOOK_ID = 1L;
    private static final Long VALID_PERSON_ID = 11L;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private Book book;

    @Mock
    private Person person;

    @Mock
    private BorrowBookDto borrowBookDto;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository, personRepository);

        when(book.getId()).thenReturn(VALID_BOOK_ID);

        when(personRepository.findById(VALID_PERSON_ID)).thenReturn(Optional.of(person));

        when(bookRepository.findById(VALID_BOOK_ID)).thenReturn(Optional.of(book));
        when(bookRepository.findById(VALID_BOOK_ID)).thenReturn(Optional.of(book));
    }

    @Test
    void shouldReturnBookWhenBookExists() {

        bookService.returnBook(VALID_BOOK_ID);

        verify(bookRepository, times(1)).findById(VALID_BOOK_ID);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void shouldNotReturnBookWhenBookIsNotPresent() {

        final Long invalidId = 2L;

        when(bookRepository.findById(invalidId)).thenReturn(Optional.empty());

        bookService.returnBook(invalidId);

        verify(bookRepository, times(1)).findById(invalidId);
        verify(bookRepository, times(0)).save(book);
    }

    @Test
    void shouldBorrowBookWhenBookAndPersonExists() {

        when(borrowBookDto.getBookId()).thenReturn(VALID_BOOK_ID);
        when(borrowBookDto.getPersonId()).thenReturn(VALID_PERSON_ID);

        final ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);

        bookService.borrowBook(borrowBookDto);

        verify(bookRepository, times(1)).findById(VALID_BOOK_ID);
        verify(personRepository, times(1)).findById(VALID_PERSON_ID);
        verify(bookRepository, times(1)).save(book);
        verify(book).setBorrowedBy(captor.capture());

        final Person capturedPerson = captor.getValue();

        assertThat(capturedPerson, CoreMatchers.equalTo(person));
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenBookDoesNotExist() {

        final Long invalidBookId = 999L;

        when(borrowBookDto.getBookId()).thenReturn(invalidBookId);
        when(borrowBookDto.getPersonId()).thenReturn(VALID_PERSON_ID);

        assertThrows(BookNotFoundException.class, () -> {
            bookService.borrowBook(borrowBookDto);
        });

        verify(bookRepository, times(1)).findById(invalidBookId);
        verify(personRepository, times(0)).findById(VALID_PERSON_ID);
    }

    @Test
    void shouldThrowPersonNotFoundExceptionWhenBookDoesNotExist() {

        final Long invalidPersonId = 999L;

        when(borrowBookDto.getBookId()).thenReturn(VALID_BOOK_ID);
        when(borrowBookDto.getPersonId()).thenReturn(invalidPersonId);

        assertThrows(PersonNotFoundException.class, () -> {
            bookService.borrowBook(borrowBookDto);
        });

        verify(bookRepository, times(1)).findById(VALID_BOOK_ID);
        verify(personRepository, times(1)).findById(invalidPersonId);
    }
}
