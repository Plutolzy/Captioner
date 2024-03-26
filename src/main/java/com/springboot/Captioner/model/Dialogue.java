package com.springboot.Captioner.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dialogue")
public class Dialogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dialogue_id")
    private int id;

    @Column(name = "subtitle_id")
    private int subtitleId;

    @Column(name = "dialogue_start_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dialogueStartTime;  // 演出开始时间

    @Column(name = "dialogue_end_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dialogueEndTime;  // 演出结束时间

    @Column(name = "dialogue_text")
    private String dialogueText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubtitleId() {
        return subtitleId;
    }

    public void setSubtitleId(int subtitleId) {
        this.subtitleId = subtitleId;
    }

    public LocalDateTime getDialogueStartTime() {
        return dialogueStartTime;
    }

    public void setDialogueStartTime(LocalDateTime dialogueStartTime) {
        this.dialogueStartTime = dialogueStartTime;
    }

    public LocalDateTime getDialogueEndTime() {
        return dialogueEndTime;
    }

    public void setDialogueEndTime(LocalDateTime dialogueEndTime) {
        this.dialogueEndTime = dialogueEndTime;
    }

    public String getDialogueText() {
        return dialogueText;
    }

    public void setDialogueText(String dialogueText) {
        this.dialogueText = dialogueText;
    }
}
