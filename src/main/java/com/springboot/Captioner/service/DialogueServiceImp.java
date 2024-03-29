package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Dialogue;
import com.springboot.Captioner.repository.DialogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Dialogue existingDialogue = dialogueRepository.findBySubtitle(dialogue.getSubtitle());
        if (existingDialogue != null) {
            dialogueExists = true;
        }
        return dialogueExists;
    }

    @Override
    public List<String> getAllSubtitleNames() {
        return dialogueRepository.findAllUniqueSubtitleNames();
    }

    @Override
    public List<Dialogue> saveAllDialogues(List<Dialogue> dialogueList, String subtitle) {
        for (Dialogue dialogue : dialogueList) {
            dialogue.setSubtitle(subtitle); // 关联 subtitleId
        }
        return dialogueRepository.saveAll(dialogueList);
    }
}
