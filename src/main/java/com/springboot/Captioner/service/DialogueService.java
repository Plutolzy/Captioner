package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Dialogue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DialogueService {

    public void saveDialogue(Dialogue dialogue);

    public boolean isDialoguePresent(Dialogue dialogue);

    public List<Dialogue> saveAllDialogues(List<Dialogue> dialogueList, int subtitleId);
}
