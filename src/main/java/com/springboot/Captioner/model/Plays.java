package com.springboot.Captioner.model;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name = "play")
public class Plays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "play_id")
    private int id;

    @Column(name = "play_title")
    private String title;


    @ManyToMany(mappedBy = "plays")
    private Set<User> users;

    public String getTitle() {
        return title;
    }
    public void setTitle(String play) {
        this.title = play;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public Set<User> getUsers(){
        return users;
    }
    public void setUsers(Set<User> users){
        this.users = users;
    }
}
