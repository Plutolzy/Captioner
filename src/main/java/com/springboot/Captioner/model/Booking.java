package com.springboot.Captioner.model;

import javax.persistence.*;

@Entity
@Table(name = "user_play")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_play_id")
    private int id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "play_title")
    private String playTitle;

    // 无参数构造器
    public Booking() {
    }

    // 带参数的构造器
    public Booking(String userEmail, String playTitle) {
        this.userEmail = userEmail;
        this.playTitle = playTitle;
    }

    // 用户的存取方法
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPlayTitle() {
        return playTitle;
    }

    public void setPlayTitle(String playTitle) {
        this.playTitle = playTitle;
    }

    // id的存取方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
