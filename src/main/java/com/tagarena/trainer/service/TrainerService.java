package com.tagarena.trainer.service;

import java.util.List;

import com.tagarena.trainer.rest.model.TrainerCreationDto;
import com.tagarena.trainer.rest.model.TrainerDto;

public interface TrainerService {

	TrainerDto getTrainer(Long trainerId);

	List<TrainerDto> createTrainers(TrainerCreationDto trainerDto);

}
