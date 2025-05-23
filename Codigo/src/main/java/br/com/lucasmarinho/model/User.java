package br.com.lucasmarinho.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "user", unique = true)
    private String user;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

}
