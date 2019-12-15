package com.furrychickens.natlib;

import com.furrychickens.natlib.model.Book;
import com.furrychickens.natlib.model.Person;
import com.furrychickens.natlib.repository.BookRepository;
import com.furrychickens.natlib.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.*;

@SpringBootApplication
public class NatlibApplication {

    public static void main(final String[] args) {
        SpringApplication.run(NatlibApplication.class, args);
    }

    @Bean
    CommandLineRunner init (final BookRepository bookRepository, final PersonRepository personRepository){
        return args -> {

            final Map<Long, List<String>> booksBorrowed = new HashMap<Long, List<String>>();
            booksBorrowed.put(0L, Arrays.asList("9780316311298", "9780399555817"));
            booksBorrowed.put(1L, Arrays.asList("9780141037639"));
            booksBorrowed.put(2L, Arrays.asList("9781760662820", "9781742999579", "9781742993263"));
            booksBorrowed.put(5L, Arrays.asList("9780349004068"));

            for (final Map.Entry<Long, List<String>> entry : booksBorrowed.entrySet()) {
                final Person person = personRepository.findById(entry.getKey()).get();

                entry.getValue().stream().forEach(isbn -> {
                    final Book book = bookRepository.findByIsbn(isbn);

                    book.setBorrowedBy(person);

                    bookRepository.save(book);
                });
            }
        };
    }

    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:8888", "http://localhost:8080"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
