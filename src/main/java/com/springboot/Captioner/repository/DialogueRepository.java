package com.springboot.Captioner.repository;

import com.springboot.Captioner.model.Dialogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DialogueRepository extends JpaRepository<Dialogue, Integer> {
    Dialogue findBySubtitle(String subtitle);

    @Query("SELECT DISTINCT d.subtitle FROM Dialogue d")
    List<String> findAllUniqueSubtitleNames();
}
