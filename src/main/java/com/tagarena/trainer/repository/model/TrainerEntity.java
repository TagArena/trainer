package com.tagarena.trainer.repository.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TrainerEntity {

	@Id
	private Long id;

	private String name;

	private Long creatureId;
}
