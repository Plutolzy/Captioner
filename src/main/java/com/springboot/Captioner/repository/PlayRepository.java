package com.springboot.Captioner.repository;

import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.PlayDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepository extends JpaRepository<Play, Integer> {
//    public Plays findByPlay(String play);
Play findByTitle(String title);

}
