package com.furrychickens.natlib.repository;

import com.furrychickens.natlib.model.Book;
import com.furrychickens.natlib.model.Person;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component
public class RestRepositoryConfigurator implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Book.class, Person.class);
    }
}
