package com.tagarena.trainer.rest;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frameboter.rest.AbstractResource;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.tagarena.trainer.rest.model.TrainerCreationDto;
import com.tagarena.trainer.rest.model.TrainerDto;
import com.tagarena.trainer.service.TrainerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class TrainerResource extends AbstractResource {

	private final TrainerService trainerService;

	private final Logger log = LoggerFactory.getLogger(TrainerResource.class);

	@Autowired
	public TrainerResource(TrainerService trainerService) {
		this.trainerService = trainerService;
	}

	// @formatter:off
    @Operation(summary = "Creates trainers acording to the given parameters", description = "Creates trainers acording to the given parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trainers created successfully"),
            @ApiResponse(responseCode = "404", description = "Trainers created successfully")
    })
    @PostMapping("/trainers")
	// @formatter:on
	List<TrainerDto> createTrainers(TrainerCreationDto trainerCreationDto) {

		log.info("Create Trainers called with {}", trainerCreationDto);
		List<TrainerDto> trainers = trainerService.createTrainers(trainerCreationDto);
		log.info("Create Trainers finished with {}", trainers);
		return trainers;
	}
}
