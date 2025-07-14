package com.tagarena.trainer.repository;

import com.tagarena.trainer.repository.model.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

}
