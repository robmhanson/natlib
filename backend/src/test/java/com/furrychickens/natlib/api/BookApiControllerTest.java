package com.furrychickens.natlib.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furrychickens.natlib.model.Book;
import com.furrychickens.natlib.repository.BookRepository;
import com.furrychickens.natlib.repository.PersonRepository;
import com.furrychickens.natlib.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookApiController.class)
public class BookApiControllerTest {

    private static final String URL = "/api/books/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommandLineRunner commandLineRunner;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private BookService bookService;

    private List<Book> booksList = new ArrayList<>();

    @BeforeEach
    void setUp() {

        final Book book1 = new Book("title1", "author1", "isbn1");
        final Book book2 = new Book("title2", "author2", "isbn2");

        booksList = Arrays.asList(book1, book2);
    }

    @Test
    public void showGetAllBooks() throws Exception {

        when(bookService.findAll()).thenReturn(booksList);

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .accept(APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        verify(bookService).findAll();

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonResponse = result.getResponse().getContentAsString();
        final List<Book> bookResults = objectMapper.readValue(jsonResponse, new TypeReference<List<Book>>() {});

        assertThat(bookResults, contains(
                allOf(hasProperty("title", is("title1")),
                        hasProperty("author", is("author1")),
                        hasProperty("isbn", is("isbn1"))),
                allOf(hasProperty("title", is("title2")),
                        hasProperty("author", is("author2")),
                        hasProperty("isbn", is("isbn2")))));
    }
}
