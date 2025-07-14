package com.tagarena.trainer.rest.model;

import lombok.*;

@Data
@AllArgsConstructor
public class TrainerDto {

	private Long id;

	private String name;

	private Long creatureId;
}
