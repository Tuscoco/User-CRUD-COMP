package br.com.lucasmarinho.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    private int id;

    @Column(name = "username", unique = true)
    private String user;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

}
