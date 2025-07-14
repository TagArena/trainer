package com.tagarena.trainer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import com.tagarena.trainer.repository.model.TrainerEntity;
import com.tagarena.trainer.repository.model.TrainerRepository;
import com.tagarena.trainer.rest.model.TrainerCreationDto;
import com.tagarena.trainer.rest.model.TrainerDto;
import com.tagarena.trainer.service.model.exception.TrainerNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

	private final TrainerRepository trainerRepository;

	private final ModelMapper modelMapper;

	public TrainerDto getTrainer(Long trainerId) {
		Optional<TrainerEntity> trainerEntityOptional = trainerRepository.findById(trainerId);
		if (!trainerEntityOptional.isPresent()) {
			throw new TrainerNotFoundException("Trainer with id " + trainerId + " not found");
		}
		return modelMapper.map(trainerEntityOptional.get(), TrainerDto.class);
	}

	public List<TrainerDto> createTrainers(TrainerCreationDto trainerCreationDto) {
		List<TrainerEntity> trainers = new ArrayList<>();
		Faker faker = new Faker();
		for (int i = 0; i < trainerCreationDto.getAmount(); i++) {
			TrainerEntity trainer = new TrainerEntity();
			trainer.setName(faker.name().fullName());
		}
		List<TrainerEntity> savedTrainers = trainerRepository.saveAll(trainers);
		// @formatter:off
		return modelMapper.map(savedTrainers, new TypeToken<List<TrainerDto>>() {}.getType());
		// @formatter:on
	}
}
