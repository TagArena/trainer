package com.tagarena.trainer.repository.model;

@Entity
@Getter
@Setter
public class TrainerEntity {

	@Id
	private Long id;

	private String name;

	private Long creatureId;
}
