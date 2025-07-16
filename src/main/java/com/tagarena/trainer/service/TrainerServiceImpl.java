package com.tagarena.trainer.service;

import com.github.javafaker.Faker;
import com.tagarena.trainer.repository.TrainerRepository;
import com.tagarena.trainer.repository.model.TrainerEntity;
import com.tagarena.trainer.rest.model.TrainerCreationDto;
import com.tagarena.trainer.rest.model.TrainerDto;
import com.tagarena.trainer.service.model.exception.TrainerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    private final ModelMapper modelMapper;

    public TrainerDto getTrainer(Long trainerId) {
        TrainerEntity trainerEntityOptional = getTrainerEntity(trainerId);
        return modelMapper.map(trainerEntityOptional, TrainerDto.class);
    }

    public List<TrainerDto> createTrainers(TrainerCreationDto trainerCreationDto) {
        List<TrainerEntity> trainers = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < trainerCreationDto.getAmount(); i++) {
            TrainerEntity trainer = new TrainerEntity();
            trainer.setName(faker.name().fullName());
            trainers.add(trainer);
        }
        List<TrainerEntity> savedTrainers = trainerRepository.saveAll(trainers);
        // @formatter:off
		return modelMapper.map(savedTrainers, new TypeToken<List<TrainerDto>>() {}.getType());
		// @formatter:on
    }

    @Override
    public TrainerDto updateTrainer(Long trainerId, TrainerDto trainerUpdate) {
        TrainerEntity trainerEntity = getTrainerEntity(trainerId);
        modelMapper.map(trainerUpdate, trainerEntity);
        trainerEntity.setId(trainerId);
        TrainerEntity updatedTrainer = trainerRepository.save(trainerEntity);
        return modelMapper.map(updatedTrainer, TrainerDto.class);
    }

    private TrainerEntity getTrainerEntity(Long trainerId) {
        Optional<TrainerEntity> trainerEntityOptional = trainerRepository.findById(trainerId);
        if (trainerEntityOptional.isEmpty()) {
            throw new TrainerNotFoundException("Trainer with id " + trainerId + " not found");
        }
        return trainerEntityOptional.get();
    }
}
