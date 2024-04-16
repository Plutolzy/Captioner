package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.PlayDTO;

import java.util.List;

public interface PlayService {
    public void savePlay(Play play);

    public boolean isPlayPresent(Play play);

    public List<Play> findPlay();

    public List<PlayDTO> getAllPlays();
}
