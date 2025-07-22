package com.tagarena.trainer.rest.model;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

    @NotNull(message = "League IDs list cannot be null")
    @Size(min = 1, message = "At least one league ID must be provided")
    private List<Long> leagueIds;

}
