package com.furrychickens.natlib.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Data
@Valid
public class BorrowBookDto {

    @NotNull
    private Long bookId;

    @NotNull
    private Long personId;
}
