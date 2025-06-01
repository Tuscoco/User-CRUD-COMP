package br.com.lucasmarinho.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private int id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    public User(){}

    public User(String username, String name, String password){

        this.setUsername(username);
        this.setName(name);
        this.setPassword(password);

    }

}
