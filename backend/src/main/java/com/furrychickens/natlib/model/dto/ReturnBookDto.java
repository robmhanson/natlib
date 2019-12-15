package com.furrychickens.natlib.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Valid
public class ReturnBookDto {

    @NotNull
    Long bookId;
}
