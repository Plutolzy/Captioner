package com.springboot.Captioner.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int id;

    @NotNull(message = "First Name cannot be empty")
    @Column(name = "admin_name")
    private String name;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email address")
    @Column(name = "admin_email")
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Length(min = 5, message = "Password should be atleast 5 characters long")
    @Column(name = "admin_password")
    private String password;

//    @Column(name = "mobile")
//    private String mobile;

//    @Column(name = "status")
//    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "admin_play",
            joinColumns = {@JoinColumn(name = "admin_id")},
            inverseJoinColumns = {@JoinColumn(name = "play_id")})
    private Set<Plays> plays;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public Set<Plays> getPlays(){
        return plays;
    }
    public void setPlays(Set<Plays> plays){
        this.plays = plays;
    }
}
