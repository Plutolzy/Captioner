package com.springboot.Captioner.model;

public class DialogueDTO {
    private int id;


    private String subtitle;

    private String dialogueStartTime;  // 演出开始时间

    private String dialogueEndTime;  // 演出结束时间

    private String dialogueText;

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
