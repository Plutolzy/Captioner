package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.PlayDTO;
import com.springboot.Captioner.repository.PlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayServiceImp implements PlayService {
    @Autowired
    PlayRepository playRepository;

    @Override
    public void savePlay(Play play) {
        play.setTitle(play.getTitle());
        play.setStartTime(play.getStartTime());
        playRepository.save(play);
    }

    @Override
    public boolean isPlayPresent(Play play) {
        boolean playExists = false;
        Play existingPlay = playRepository.findByTitle(play.getTitle());
        if(existingPlay!=null){
            playExists = true;
        }
        return playExists;
    }

    @Override
    public List<Play> findPlay(){
        return playRepository.findAll();
    }

    // 将 Play 实体转换为 PlayDTO
    public List<PlayDTO> getAllPlays() {
        List<Play> plays = playRepository.findAll();
        return plays.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PlayDTO convertToDTO(Play play) {
        PlayDTO dto = new PlayDTO();
        dto.setId(play.getId());
        dto.setTitle(play.getTitle());
        dto.setStartTime(play.getStartTime());
        dto.setEndTime(play.getEndTime());
        return dto;
    }
}
