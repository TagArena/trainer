package com.tagarena.trainer.rest.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TrainerDto {

    private Long id;

    private String name;

    private List<Long> leagueIds;

}
