package com.tagarena.trainer.rest;

import com.tagarena.trainer.rest.model.TrainerCreationDto;
import com.tagarena.trainer.rest.model.TrainerDto;
import com.tagarena.trainer.service.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TrainerResource {

    private final TrainerService trainerService;

    // @formatter:off
    @Operation(summary = "Creates trainers according to the given parameters", description = "Creates trainers according to the given parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trainers created successfully")
    })
    @PostMapping("/trainers")
	// @formatter:on
    List<TrainerDto> createTrainers(@RequestBody @Valid TrainerCreationDto trainerCreationDto) {

        log.info("createTrainers called with {}", trainerCreationDto);
        List<TrainerDto> trainers = trainerService.createTrainers(trainerCreationDto);
        log.info("createTrainers finished with trainers={}", trainers);

        return trainers;
    }

    // @formatter:off
    @Operation(summary = "Updates the trainer wit hthe path id to the given trainer", description = "Updates the trainer wit hthe path id to the given trainer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trainers created successfully"),
            @ApiResponse(responseCode = "404", description = "Trainer not found")
    })
    @PostMapping("/trainers/{trainerId}")
    // @formatter:on
    TrainerDto updateTrainer(@PathVariable @NotNull Long trainerId, @RequestBody @Valid TrainerDto trainerUpdate) {

        log.info("updateTrainer called with trainerid={}, trainerUpdate={}", trainerId, trainerUpdate);
        TrainerDto updatedTrainer = trainerService.updateTrainer(trainerId, trainerUpdate);
        log.info("updateTrainer finished with updatedTrainer={}", updatedTrainer);

        return updatedTrainer;
    }

    // @formatter:off
	@Operation(summary = "Retrieves a trainer by their trainerId", description = "Retrieves a trainer by their trainerId")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Trainer retrieved successfully"),
		@ApiResponse(responseCode = "404", description = "Trainer not found")
	})
	@GetMapping("/trainers/{id}")
	// @formatter:on
    TrainerDto getTrainer(@NotNull @PathVariable Long id) {

        log.info("getTrainer called with id={}", id);
        TrainerDto trainer = trainerService.getTrainer(id);
        log.info("getTrainer finished with trainer={}", trainer);

        return trainer;
    }
}
