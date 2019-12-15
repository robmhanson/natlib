package com.furrychickens.natlib.model;

import com.furrychickens.natlib.model.dto.PersonDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String phone;

    @NonNull
    private String email;

    @OneToMany(mappedBy = "borrowedBy", cascade = { CascadeType.ALL })
    private List<Book> books;

    public PersonDto toPersonDto() {
        return new PersonDto(id, firstName, lastName, phone, email);
    }
}
