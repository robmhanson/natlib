package com.furrychickens.natlib.api;

import com.furrychickens.natlib.model.Person;
import com.furrychickens.natlib.model.dto.PersonDto;
import com.furrychickens.natlib.service.PersonService;
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
@RequestMapping("/api/people")
public class PersonApiController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDto>> findAll() {

        final List<PersonDto> personDtoList =
                personService.findAll().stream().map(person -> person.toPersonDto()).collect(Collectors.toList());

        return ResponseEntity.ok(personDtoList);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<PersonDto> findById(@PathVariable Long personId) {
        Optional<Person> personOptional = personService.findById(personId);
        if (!personOptional.isPresent()) {
            log.error("PersonId " + personId + " does not exist");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(personOptional.get().toPersonDto());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
    }

}
