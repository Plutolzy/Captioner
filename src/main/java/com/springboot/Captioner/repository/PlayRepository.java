package com.springboot.Captioner.repository;

import com.springboot.Captioner.model.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends JpaRepository<Play, Integer> {
    //    public Plays findByPlay(String play);
    Play findByTitle(String title);

}
