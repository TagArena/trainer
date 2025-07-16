package com.tagarena.trainer.rest;

import com.tagarena.trainer.repository.TrainerRepository;
import com.tagarena.trainer.repository.model.TrainerEntity;
import com.tagarena.trainer.rest.model.TrainerCreationDto;
import com.tagarena.trainer.rest.model.TrainerDto;
import com.tagarena.trainer.service.model.exception.TrainerNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Log4j2
class TrainerResourceTest {

    private final TrainerResource trainerResource;

    @MockBean
    private TrainerRepository trainerRepository;

    @Captor
    ArgumentCaptor<List<TrainerEntity>> trainerEntityArgumentCaptor;

    @Autowired
    public TrainerResourceTest(TrainerResource trainerResource) {
        this.trainerResource = trainerResource;
    }

    @Test
    void createTrainers_amount2_saved_2trainers() {

        log.info("Started testing createTrainers_amount2_saved_2trainers");

        TrainerEntity trainerMock1 = new TrainerEntity(1L, "Test", 1L);
        TrainerEntity trainerMock2 = new TrainerEntity(2L, "Test", 2L);

        when(trainerRepository.saveAll(any())).thenReturn(List.of(trainerMock1, trainerMock2));

        TrainerCreationDto TrainerCreationDto = new TrainerCreationDto(2L);
        List<TrainerDto> result = trainerResource.createTrainers(TrainerCreationDto);

        assertEquals(2, result.size());
        TrainerDto expectedTrainer1 = new TrainerDto(1L, "Test", 1L);
        assertEquals(expectedTrainer1, result.getFirst());
        TrainerDto expectedTrainer2 = new TrainerDto(2L, "Test", 2L);
        assertEquals(expectedTrainer2, result.get(1));

        verify(trainerRepository, times(1)).saveAll(any());

        log.info("Finished testing createTrainers_amount2_saved_2trainers");
    }

    @Test
    void createTrainers_name_generated() {

        log.info("Started testing createTrainers_name_generated");

        when(trainerRepository.saveAll(any())).thenReturn(List.of(new TrainerEntity(1L, "Test", 1L)));

        TrainerCreationDto TrainerCreationDto = new TrainerCreationDto(1L);
        trainerResource.createTrainers(TrainerCreationDto);

        verify(trainerRepository, times(1)).saveAll(trainerEntityArgumentCaptor.capture());
        TrainerEntity capturedTrainer = trainerEntityArgumentCaptor.getValue().getFirst();
        assertNotNull(capturedTrainer.getName());

        log.info("Finished testing createTrainers_name_generated");
    }

    @Test
    void getTrainer_mapping_correct() {

        log.info("Started testing getTrainer_mapping_correct");

        TrainerEntity mockTrainerEntity = new TrainerEntity(1L, "Test", 1L);
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(mockTrainerEntity));

        TrainerDto result = trainerResource.getTrainer(1L);

        TrainerDto expected = new TrainerDto(1L, "Test", 1L);
        assertEquals(expected, result);

        log.info("Finished testing getTrainer_mapping_correct");
    }

    @Test
    void getTrainer_trainer_notexistent_throws_exception() {

        log.info("Started testing getTrainer_trainer_notexistent_throws_exception");

        when(trainerRepository.findById(1L)).thenReturn(Optional.empty());

        TrainerNotFoundException trainerNotFoundException = assertThrows(TrainerNotFoundException.class, () -> trainerResource.getTrainer(1L));
        assertTrue(trainerNotFoundException.getMessage().contains("Trainer with id 1 not found"));

        log.info("Finished testing getTrainer_trainer_notexistent_throws_exception");
    }

    @Test
    void update_trainer_notexistent_throws_exception() {

        log.info("Started testing update_trainer_notexistent_throws_exception");

        when(trainerRepository.findById(1L)).thenReturn(Optional.empty());

        TrainerDto requestTrainer = new TrainerDto();
        TrainerNotFoundException trainerNotFoundException = assertThrows(TrainerNotFoundException.class, () -> trainerResource.updateTrainer(1L, requestTrainer));
        assertTrue(trainerNotFoundException.getMessage().contains("Trainer with id 1 not found"));

        log.info("Finished testing update_trainer_notexistent_throws_exception");
    }

    @Test
    void update_trainer_mapping_correct() {

        log.info("Started testing update_trainer_mapping_correct");

        TrainerEntity trainerMock2 = new TrainerEntity(3L, "Test", 3L);
        when(trainerRepository.findById(3L)).thenReturn(Optional.of(trainerMock2));

        when(trainerRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        TrainerDto requestTrainer = new TrainerDto(4L, "Test1", 4L);
        TrainerDto result = trainerResource.updateTrainer(3L, requestTrainer);

        TrainerDto expected = new TrainerDto(3L, "Test1", 4L);
        assertEquals(expected, result);

        log.info("Finished testing update_trainer_mapping_correct");
    }
}