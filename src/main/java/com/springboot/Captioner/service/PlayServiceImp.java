package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Plays;
import com.springboot.Captioner.repository.PlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayServiceImp implements PlayService {
    @Autowired
    PlayRepository playRepository;

    @Override
    public void savePlay(Plays play) {
        play.setTitle(play.getTitle());
        play.setStartTime(play.getStartTime());
        playRepository.save(play);
    }

    @Override
    public boolean isPlayPresent(Plays play) {
        boolean playExists = false;
        Plays existingPlay = playRepository.findByTitle(play.getTitle());
        if(existingPlay!=null){
            playExists = true;
        }
        return playExists;
    }
}
