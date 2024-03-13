package com.springboot.Captioner.repository;

import com.springboot.Captioner.model.Plays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends JpaRepository<Plays, Integer> {
//    public Plays findByPlay(String play);
    public Plays findByTitle(String title);
}
