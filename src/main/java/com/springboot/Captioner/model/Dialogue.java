package com.springboot.Captioner.model;

import javax.persistence.*;

@Entity
@Table(name = "dialogue")
public class Dialogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dialogue_id")
    private int id;

    @Column(name = "subtitle_name")
    private String subtitle;

    @Column(name = "dialogue_start_time")
    private String dialogueStartTime;  // 演出开始时间

    @Column(name = "dialogue_end_time")
    private String dialogueEndTime;  // 演出结束时间

    @Column(name = "dialogue_text")
    private String dialogueText;

    public Dialogue() {
    }

    public Dialogue(String startTime, String endTime, String text) {
        this.dialogueStartTime = startTime;
        this.dialogueEndTime = endTime;
        this.dialogueText = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDialogueStartTime() {
        return dialogueStartTime;
    }

    public void setDialogueStartTime(String dialogueStartTime) {
        this.dialogueStartTime = dialogueStartTime;
    }

    public String getDialogueEndTime() {
        return dialogueEndTime;
    }

    public void setDialogueEndTime(String dialogueEndTime) {
        this.dialogueEndTime = dialogueEndTime;
    }

    public String getDialogueText() {
        return dialogueText;
    }

    public void setDialogueText(String dialogueText) {
        this.dialogueText = dialogueText;
    }
}
