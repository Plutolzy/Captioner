package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Dialogue;
import org.springframework.stereotype.Service;

@Service
public interface DialogueService {

    public void saveDialogue(Dialogue dialogue);

    public boolean isDialoguePresent(Dialogue dialogue);
}
