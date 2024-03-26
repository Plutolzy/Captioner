package com.springboot.Captioner.repository;

import com.springboot.Captioner.model.Dialogue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DialogueRepository extends JpaRepository<Dialogue, Integer> {
    Dialogue findBySubtitleId(int subtitleId);
}
