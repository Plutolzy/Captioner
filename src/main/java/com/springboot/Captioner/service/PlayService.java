package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Play;

public interface PlayService {
    public void savePlay(Play play);
    public boolean isPlayPresent(Play play);
}
