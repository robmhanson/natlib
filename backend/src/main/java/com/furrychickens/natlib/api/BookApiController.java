package com.furrychickens.natlib.api;

import com.furrychickens.natlib.model.Book;
import com.furrychickens.natlib.model.dto.BookDto;
import com.furrychickens.natlib.model.dto.BorrowBookDto;
import com.furrychickens.natlib.model.dto.BorrowedBookDto;
import com.furrychickens.natlib.model.dto.ReturnBookDto;
import com.furrychickens.natlib.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookApiController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {

        final List<BookDto> bookDtoList =
                bookService.findAll().stream().map(book -> book.toBookDto()).collect(Collectors.toList());

        return ResponseEntity.ok(bookDtoList);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> findById(@PathVariable Long bookId) {
        Optional<Book> bookOptional = bookService.findById(bookId);
        if (!bookOptional.isPresent()) {
            log.error("BookId " + bookId + " does not exist");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(bookOptional.get().toBookDto());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));
    }

    @GetMapping("borrowed/{personId}")
    public List<BorrowedBookDto> borrowed(@PathVariable(value = "personId") Long personId) {
        final List<Book> books = bookService.findByBorrowedById(personId);

        return books.stream().map(book -> book.toBorrowedBook()).collect(Collectors.toList());
    }

    @PostMapping("/returnBook")
    public ResponseEntity returnBook(@RequestBody ReturnBookDto returnBookDto) {
        bookService.returnBook(returnBookDto.getBookId());

        return ResponseEntity.accepted().build();
    }

    @PostMapping("/borrowBook")
    public ResponseEntity borrowBook(@RequestBody BorrowBookDto borrowBookDto) {
        bookService.borrowBook(borrowBookDto);

        return ResponseEntity.accepted().build();
    }
}
