package com.springboot.Captioner.service;

import com.springboot.Captioner.model.Dialogue;
import com.springboot.Captioner.model.DialogueDTO;
import com.springboot.Captioner.repository.DialogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        Dialogue existingDialogue = dialogueRepository.findBySubtitleAndDialogueEndTime(dialogue.getSubtitle(), dialogue.getDialogueStartTime());
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

    @Override
    public List<DialogueDTO> getCurrentDialogueList(String subtitle, String playStartTime, String currentTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(playStartTime, dateTimeFormatter);
        LocalDateTime nowString = LocalDateTime.parse(currentTime, dateTimeFormatter);


        // 获取与当前 subtitle 相关的所有对话列表
        List<Dialogue> dialogueList = dialogueRepository.findBySubtitle(subtitle);
        List<DialogueDTO> currentAndFutureDialogues = new ArrayList<>(); // 存放当前及之后的对话列表

        // 计算播放开始了多久
        Duration playDuration = Duration.between(startTime, nowString).abs();

// 将playDuration转换为时分秒的字符串格式
        String playDurationStr = String.format("%02d:%02d:%02d",
                playDuration.toHours(),
                playDuration.toMinutesPart(),
                playDuration.toSecondsPart());

        // 遍历对话列表，找到最接近播放已进行时间的对话
        for (Dialogue dialogue : dialogueList) {
            // 解析对话开始时间字符串
            String dialogueStartTimeStr = dialogue.getDialogueStartTime();
            // 解析对话开始时间为 LocalDateTime 对象

            // 如果对话开始时间在播放已进行时间之后或等于播放已进行时间，则添加到列表中
            if (dialogueStartTimeStr.compareTo(playDurationStr) <= 0) {
                DialogueDTO dialogueDTO = convertToDialogueDTO(dialogue);
                System.out.println("DialogueDTO start time type: " + dialogueDTO.getDialogueStartTime().getClass());
                currentAndFutureDialogues.add(dialogueDTO);
            }
        }

        return currentAndFutureDialogues;
    }

    public List<DialogueDTO> getAllDialogueDTOs() {
        List<Dialogue> dialogues = dialogueRepository.findAll();
        return dialogues.stream().map(this::convertToDialogueDTO).collect(Collectors.toList());
    }

    public DialogueDTO convertToDialogueDTO(Dialogue dialogue) {
        DialogueDTO dto = new DialogueDTO();
        dto.setId(dialogue.getId());
        dto.setDialogueStartTime(dialogue.getDialogueStartTime());
        dto.setSubtitle(dialogue.getSubtitle());
        dto.setDialogueEndTime(dialogue.getDialogueEndTime());
        dto.setDialogueText(dialogue.getDialogueText());
        return dto;
    }
}

