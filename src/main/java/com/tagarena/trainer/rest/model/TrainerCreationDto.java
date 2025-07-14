package com.tagarena.trainer.rest.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TrainerCreationDto {

    @NotNull(message = "Amount must be higher than 0")
    @Min(value = 1, message = "Amount must be higher than 0")
    private Long amount;

}
