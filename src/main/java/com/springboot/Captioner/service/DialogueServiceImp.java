package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Dialogue;
import com.springboot.Captioner.repository.DialogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DialogueServiceImp implements DialogueService {

    @Autowired
    private DialogueRepository dialogueRepository;


    @Override
    public void saveDialogue(Dialogue dialogue) {
        dialogueRepository.save(dialogue);
    }

    @Override
    public boolean isDialoguePresent(Dialogue dialogue) {
        boolean dialogueExists = false;
        Dialogue existingDialogue = dialogueRepository.findBySubtitleId(dialogue.getSubtitleId());
        if (existingDialogue != null) {
            dialogueExists = true;
        }
        return dialogueExists;
    }
}
