package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Dialogue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DialogueService {

    void saveDialogue(Dialogue dialogue);

    boolean isDialoguePresent(Dialogue dialogue);

    List<String> getAllSubtitleNames();

    List<Dialogue> saveAllDialogues(List<Dialogue> dialogueList, String subtitle);

    List<Dialogue> getCurrentDialogueList(String subtitle, String startTime, String currentTime);

}
