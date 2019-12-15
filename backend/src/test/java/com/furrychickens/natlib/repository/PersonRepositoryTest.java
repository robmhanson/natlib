package com.furrychickens.natlib.repository;

import com.furrychickens.natlib.model.Person;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void shouldSavePerson() {
        final Person person = new Person();
        person.setFirstName("MyFirstName");
        person.setLastName("MyLastName");
        person.setPhone("MyNumber");
        person.setEmail("MyEmail");

        final Person updatedPerson = personRepository.save(person);

        assertThat(updatedPerson, CoreMatchers.notNullValue());
        assertThat(updatedPerson.getId(), CoreMatchers.notNullValue());
    }
}
