package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Plays;

public interface PlayService {
    public void savePlay(Plays play);
    public boolean isPlayPresent(Plays play);
}
